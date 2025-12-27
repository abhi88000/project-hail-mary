package com.fzm.phm.service;

import com.fzm.phm.dto.SlotRequest;
import com.fzm.phm.model.Doctor;
import com.fzm.phm.model.Slot;
import com.fzm.phm.model.Tenant;
import com.fzm.phm.repository.DoctorRepository;
import com.fzm.phm.repository.SlotRepository;
import com.fzm.phm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public int generateSlots(SlotRequest request) {

        // 1. Validate basic inputs
        validateRequest(request);

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        LocalDate startDate = request.getStartDate();
        LocalDate endDate = startDate.plusDays(request.getNumberOfDays() - 1);

        // 2. Overwrite existing slots
        slotRepository.deleteByDoctorIdAndDateBetween(
                doctor.getId(),
                startDate,
                endDate
        );

        // 3. Generate slots
        List<Slot> slotsToSave = new ArrayList<>();

        for (int day = 0; day < request.getNumberOfDays(); day++) {

            LocalDate currentDate = startDate.plusDays(day);
            LocalTime currentTime = request.getLoginTime();

            while (currentTime.plusMinutes(request.getSlotDurationMinutes())
                    .compareTo(request.getLogoutTime()) <= 0) {

                LocalTime slotEnd = currentTime.plusMinutes(request.getSlotDurationMinutes());

                // Skip break time
                if (isInBreak(currentTime, slotEnd, request)) {
                    currentTime = slotEnd;
                    continue;
                }

                Slot slot = new Slot();
                slot.setTenant(tenant);
                slot.setDoctor(doctor);
                slot.setDate(currentDate);
                slot.setStartTime(currentTime);
                slot.setEndTime(slotEnd);
                slot.setStatus("AVAILABLE");

                slotsToSave.add(slot);

                currentTime = slotEnd;
            }
        }

        // 4. Save all slots
        slotRepository.saveAll(slotsToSave);

        // 5. Return count
        return slotsToSave.size();
    }

    // ---------------- Helper methods ----------------

    private void validateRequest(SlotRequest request) {
        if (request.getLoginTime().compareTo(request.getLogoutTime()) >= 0) {
            throw new IllegalArgumentException("Login time must be before logout time");
        }

        if (request.getSlotDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Slot duration must be positive");
        }

        if (request.getNumberOfDays() <= 0) {
            throw new IllegalArgumentException("Number of days must be greater than zero");
        }
    }

    private boolean isInBreak(LocalTime start, LocalTime end, SlotRequest request) {
        if (request.getBreakStart() == null || request.getBreakEnd() == null) {
            return false;
        }

        return !(end.compareTo(request.getBreakStart()) <= 0
                || start.compareTo(request.getBreakEnd()) >= 0);
    }
}
