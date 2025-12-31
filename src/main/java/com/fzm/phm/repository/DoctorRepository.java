package com.fzm.phm.repository;

import com.fzm.phm.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByIdAndTenantId(UUID id, UUID tenantId);
}
