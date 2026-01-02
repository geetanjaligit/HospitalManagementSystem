package com.project.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalManagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
