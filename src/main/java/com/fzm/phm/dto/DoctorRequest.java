package com.fzm.phm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorRequest {

    private UUID tenantId;
    private String name;
    private String specialization;
}
