package com.spring.microservice.patient.repo;

import com.spring.microservice.patient.model.PatientLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientLoginRepo extends JpaRepository<PatientLogin,String> {
}
