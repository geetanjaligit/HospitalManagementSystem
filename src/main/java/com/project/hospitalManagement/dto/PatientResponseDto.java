package com.project.hospitalManagement.dto;

import java.time.LocalDate;

import com.project.hospitalManagement.entity.type.BloodGroupType;

import lombok.Data;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}