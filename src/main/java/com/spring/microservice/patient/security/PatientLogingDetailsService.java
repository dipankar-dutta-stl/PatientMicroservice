package com.spring.microservice.patient.security;

import com.spring.microservice.patient.model.PatientLogin;
import com.spring.microservice.patient.repo.PatientLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientLogingDetailsService implements UserDetailsService {
    @Autowired
    PatientLoginRepo patientLoginRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PatientLogin patientLogin=patientLoginRepo.findById(username).get();
        if(patientLogin!=null){
            return new PatientLoginDetails(patientLogin);
        }
        return null;
    }

}
