package com.cas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doctor {
    private Long id;
    private String degree;
    private String specialization;
    // Foreign key
    private StaffEntity staffEntity;
}
