package com.cas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Appointment {
    private Long doctorId;
    private Long patientId;
    private Date date;
    private Integer numberInQueue;
    private String comment;
    // Foreign keys
    private Doctor doctor;
    private Patient patient;
}
