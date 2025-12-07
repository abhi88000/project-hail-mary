package com.fzm.phm.service;

import com.fzm.phm.dto.AppointmentRequest;
import com.fzm.phm.dto.AppointmentResponse;
import com.fzm.phm.model.Appointment;
import com.fzm.phm.model.Patient;
import com.fzm.phm.model.Tenant;
import com.fzm.phm.repository.AppointmentRepository;
import com.fzm.phm.repository.PatientRepository;
import com.fzm.phm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PatientRepository patientRepository;

    public AppointmentResponse createAppointment(AppointmentRequest request) {

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setTenant(tenant);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(request.getStatus());
        appointment.setNotes(request.getNotes());

        Appointment saved = appointmentRepository.save(appointment);

        return mapToResponse(saved);
    }

    private AppointmentResponse mapToResponse(Appointment a) {
        AppointmentResponse res = new AppointmentResponse();

        res.setId(a.getId());
        res.setTenantId(a.getTenant().getId());
        res.setPatientId(a.getPatient().getId());
        res.setAppointmentTime(a.getAppointmentTime());
        res.setStatus(a.getStatus());
        res.setNotes(a.getNotes());

        return res;
    }
}
