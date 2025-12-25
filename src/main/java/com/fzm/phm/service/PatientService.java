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

        // 1. Validate phone number (business rule)
        validatePhone(request.getPhone());

        // 2. Fetch tenant entity (foreign key resolution)
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        // 3. Create entity from DTO
        Patient patient = new Patient();
        patient.setTenant(tenant);
        patient.setFullName(request.getFullName());
        patient.setPhone(request.getPhone());
        patient.setEmail(request.getEmail());

        // 4. Persist entity
        Patient saved = patientRepository.save(patient);

        // 5. Convert entity to response DTO
        return mapToResponse(saved);
    }

    // ---- Helper methods ----

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches("^[6-9][0-9]{9}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    private PatientResponse mapToResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setTenantId(patient.getTenant().getId());
        response.setFullName(patient.getFullName());
        response.setPhone(patient.getPhone());
        response.setEmail(patient.getEmail());
        return response;
    }
}
