package com.fzm.phm.dto;

import java.time.Instant;
import java.util.UUID;

public class AppointmentRequest {
    private UUID tenantId;
    private UUID patientId;
    private Instant appointmentTime;
    private String status;
    private String notes;

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public Instant getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Instant appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
