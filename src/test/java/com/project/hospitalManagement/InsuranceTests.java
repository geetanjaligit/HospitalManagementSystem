package com.project.hospitalManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalManagement.entity.Appointment;
import com.project.hospitalManagement.entity.Insurance;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.AppointmentRepository;
import com.project.hospitalManagement.repository.PatientRepository;
import com.project.hospitalManagement.service.AppointmentService;
import com.project.hospitalManagement.service.InsuranceService;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Test
    public void testInsurance()
    {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();
        Patient patient=insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);
        
        var newPatient=insuranceService.dissociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment()
    {
        Appointment appointment=Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,14,0))
                .reason("Cancer")
                .build();
    
        // var newAppointment=appointmentService.createNewAppointment(appointment,1L,2L);
        // System.out.println(newAppointment);

        // var updatedAppointment=appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(),3L);
        // System.out.println(updatedAppointment);

    }
    // @Test
    // public void testCreateAppointmentAndDel()
    // {  
    //     List<Long> appointmentIds = new ArrayList<>();

    //     for (int i = 0; i < 3; i++) {
    //         Appointment appointment = Appointment.builder()
    //                 .appointmentTime(LocalDateTime.of(2025, 11, 1 + i, 14, 0))
    //                 .reason("Checkup " + i)
    //                 .build();
    //         Appointment saved = appointmentService.createNewAppointment(appointment, 1L, 3L);
    //         appointmentIds.add(saved.getId());
    //     }

    //     patientRepository.deleteById(3L);

    //     for (Long appId : appointmentIds) {
    //         System.out.println("Appointment " + appId + " exists: " + appointmentRepository.existsById(appId));
    //     }
    // }
}
