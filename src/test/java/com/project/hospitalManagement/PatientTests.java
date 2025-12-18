package com.project.hospitalManagement;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.project.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.entity.type.BloodGroupType;
import com.project.hospitalManagement.repository.PatientRepository;


@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testPatientRepository() {
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethods()
    {
        // List<Patient> patientList=patientRepository.findByBornAfterDate(LocalDate.of(1998,3,14));
        // for(Patient patient:patientList)
        // {
        //     System.out.println(patient);
        // }
        // List<Object[]> bloodGroupList=patientRepository.countEachBloodGroupType();
        // for(Object[] objects:bloodGroupList)
        // {
        //     System.out.println(objects[0]+" "+objects[1]);
        // } 

        // List<BloodGroupCountResponseEntity> bloodGroupList=patientRepository.countEachBloodGroupType();
        // for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupList)
        // {
        //     System.out.println(bloodGroupCountResponse);
        // }
        Page<Patient> patientList=patientRepository.findAllPatients(PageRequest.of(0,2 ));
        for(Patient patient:patientList)
        {
            System.out.println(patient);
        }

        //update a row
        // int rowsUpdated=patientRepository.updateNameWithId("Arav Sharma",1L);
        // System.out.println(rowsUpdated);
    }

    
}

