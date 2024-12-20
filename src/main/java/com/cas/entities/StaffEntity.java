package com.cas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffEntity {
    private Long id;
    private Double salary;
    // Foreign key
    private Patient patient;
}
