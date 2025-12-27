package com.fzm.phm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class SlotResponse {

    private UUID slotId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
}
