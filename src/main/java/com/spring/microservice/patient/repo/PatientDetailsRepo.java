package com.spring.microservice.patient.repo;

import com.spring.microservice.patient.model.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientDetailsRepo extends JpaRepository<PatientDetails,String> {

    @Query(value = "select * from patient_details where email_id=?",nativeQuery = true)
    public PatientDetails findByEmail(String email);
}
