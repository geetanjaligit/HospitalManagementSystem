package com.project.hospitalManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.entity.type.BloodGroupType;

import java.util.List;
import java.time.LocalDate;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
    List<Patient> findByBirthDate(LocalDate birthDate,String email);
    List<Patient> findByBirthDateBetween(LocalDate startDate,LocalDate endDate);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("select p from Patient p where p.bloodGroup = :bloodGroup")
    List<Patient> findByBloodGroup(@Param("bloodGroup")BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate")LocalDate birthDate); 

    // @Query("select p.bloodGroup,Count(p) from Patient p group by p.bloodGroup ")
    // List<Object[]> countEachBloodGroupType();

    //using projection
    @Query("select new com.project.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, Count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();


    @Query(value="select * from patient",nativeQuery=true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name")String name,@Param("id") Long id);

}
 