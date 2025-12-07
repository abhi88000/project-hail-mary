package com.fzm.phm.service;

import com.fzm.phm.dto.PatientRequest;
import com.fzm.phm.dto.PatientResponse;
import com.fzm.phm.model.Patient;
import com.fzm.phm.model.Tenant;
import com.fzm.phm.repository.PatientRepository;
import com.fzm.phm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public PatientResponse createPatient(PatientRequest request) {

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Patient patient = new Patient();
        patient.setTenant(tenant);
        patient.setFullName(request.getFullName());
        patient.setPhone(request.getPhone());
        patient.setEmail(request.getEmail());

        Patient saved = patientRepository.save(patient);

        return mapToResponse(saved);
    }

    private PatientResponse mapToResponse(Patient p) {
        PatientResponse res = new PatientResponse();

        res.setId(p.getId());
        res.setTenantId(p.getTenant().getId());
        res.setFullName(p.getFullName());
        res.setPhone(p.getPhone());
        res.setEmail(p.getEmail());

        return res;
    }
}
