package com.fzm.phm.repository;

import com.fzm.phm.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface SlotRepository extends JpaRepository<Slot, UUID> {

    void deleteByDoctorIdAndDateBetween(
            UUID doctorId,
            LocalDate start,
            LocalDate end
    );
}
