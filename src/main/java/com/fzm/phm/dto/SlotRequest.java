package com.fzm.phm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class SlotRequest {

    private UUID tenantId;
    private UUID doctorId;

    private LocalDate startDate;
    private int numberOfDays;

    private LocalTime loginTime;
    private LocalTime logoutTime;

    private LocalTime breakStart;
    private LocalTime breakEnd;

    private int slotDurationMinutes; // default 15
}
