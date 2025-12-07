package com.fzm.phm.repository;

import com.fzm.phm.model.Patient;
import com.fzm.phm.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    List<Patient> findByTenant(Tenant tenant);
}
