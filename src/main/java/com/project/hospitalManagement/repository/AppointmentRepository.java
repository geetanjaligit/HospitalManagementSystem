package com.project.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalManagement.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

}
