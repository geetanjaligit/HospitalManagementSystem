package com.project.hospitalManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.project.hospitalManagement.entity.type.BloodGroupType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
@Table(
       name="patient",
       uniqueConstraints = {
            @UniqueConstraint(name="unique_patient_email",columnNames = {"email"}),
            @UniqueConstraint(name="unique_patient_name_birthdate",columnNames = {"name","birth_date"})
       },
       indexes = {
        @Index(name="idx_patient_birth_date",columnList="birth_date")
       }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=40)
    private String name;

    private LocalDate birthDate;

    @Column(unique=true,nullable=false)
    private String email;
    private String gender;
    

    @CreationTimestamp
    @Column(updatable=false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}
 