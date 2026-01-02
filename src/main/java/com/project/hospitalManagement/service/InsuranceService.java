package com.project.hospitalManagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.hospitalManagement.entity.Insurance;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.InsuranceRepository;
import com.project.hospitalManagement.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId)
    {
        Patient patient =patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);//to maintain bidirectional consistency
        
        return patient;
    }

    @Transactional
    public Patient dissociateInsuranceFromPatient(Long patientId)
    {
        Patient patient= patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+patientId));
        
        patient.setInsurance(null);
        return patient;
    }
}
