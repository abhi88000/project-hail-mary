package com.fzm.phm.dto;

import java.time.Instant;
import java.util.UUID;

public class DoctorResponse {
    private UUID id;
    private UUID tenantId;
    private String name;
    private String specialization;
    private Instant createdAt;
}

