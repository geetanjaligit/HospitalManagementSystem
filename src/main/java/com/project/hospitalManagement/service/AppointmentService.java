package com.project.hospitalManagement.service;

import org.springframework.stereotype.Service;

import com.project.hospitalManagement.entity.Appointment;
import com.project.hospitalManagement.entity.Doctor;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.AppointmentRepository;
import com.project.hospitalManagement.repository.DoctorRepository;
import com.project.hospitalManagement.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment,Long doctorId,Long patientId)
    {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient= patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId()!=null)
            throw new IllegalArgumentException("Appointment should not have prior id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId)
    {
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        return appointment;
    }
}
