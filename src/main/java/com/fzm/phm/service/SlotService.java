package com.fzm.phm.service;

import com.fzm.phm.dto.SlotRequest;
import com.fzm.phm.dto.SlotResponse;
import com.fzm.phm.model.Doctor;
import com.fzm.phm.model.Slot;
import com.fzm.phm.model.Tenant;
import com.fzm.phm.repository.DoctorRepository;
import com.fzm.phm.repository.SlotRepository;
import com.fzm.phm.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final DoctorRepository doctorRepository;
    private final TenantRepository tenantRepository;

    public SlotService(SlotRepository slotRepository,
                       DoctorRepository doctorRepository,
                       TenantRepository tenantRepository) {
        this.slotRepository = slotRepository;
        this.doctorRepository = doctorRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<SlotResponse> createSlots(SlotRequest request) {

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Doctor doctor = doctorRepository
                .findByIdAndTenantId(request.getDoctorId(), request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // overwrite old slots for that day
        List<Slot> existingSlots =
                slotRepository.findByDoctorIdAndDate(doctor.getId(), request.getDate());
        slotRepository.deleteAll(existingSlots);

        List<Slot> slotsToSave = new ArrayList<>();

        LocalTime currentTime = request.getLoginTime();

        while (!currentTime.plusMinutes(request.getSlotDurationMinutes())
                .isAfter(request.getLogoutTime())) {

            Slot slot = new Slot();
            slot.setTenant(tenant);
            slot.setDoctor(doctor);
            slot.setDate(request.getDate());
            slot.setStartTime(currentTime);
            slot.setEndTime(currentTime.plusMinutes(request.getSlotDurationMinutes()));
            slot.setStatus("AVAILABLE");

            slotsToSave.add(slot);
            currentTime = currentTime.plusMinutes(request.getSlotDurationMinutes());
        }

        return slotRepository.saveAll(slotsToSave)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SlotResponse mapToResponse(Slot slot) {
        SlotResponse res = new SlotResponse();
        res.setId(slot.getId());
        res.setDoctorId(slot.getDoctor().getId());
        res.setDate(slot.getDate());
        res.setStartTime(slot.getStartTime());
        res.setEndTime(slot.getEndTime());
        res.setStatus(slot.getStatus());
        return res;
    }
}
