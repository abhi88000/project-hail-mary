package com.fzm.phm.repository;

import com.fzm.phm.model.Appointment;
import com.fzm.phm.model.Patient;
import com.fzm.phm.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByTenant(Tenant tenant);
    List<Appointment> findByPatient(Patient patient);
}
