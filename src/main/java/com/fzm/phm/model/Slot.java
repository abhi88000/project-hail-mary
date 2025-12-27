package com.fzm.phm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "slot")
public class Slot {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private Tenant tenant;

    @ManyToOne(optional = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String status; // AVAILABLE, BOOKED
}
