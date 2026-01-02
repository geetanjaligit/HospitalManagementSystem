package com.project.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalManagement.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

}
