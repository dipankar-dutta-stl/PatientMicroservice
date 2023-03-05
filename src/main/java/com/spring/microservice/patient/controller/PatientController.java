package com.spring.microservice.patient.controller;

import com.spring.microservice.patient.jwt.JwtUtills;
import com.spring.microservice.patient.model.Patient;
import com.spring.microservice.patient.model.PatientDetails;
import com.spring.microservice.patient.model.PatientLogin;
import com.spring.microservice.patient.repo.PatientDetailsRepo;
import com.spring.microservice.patient.repo.PatientLoginRepo;
import com.spring.microservice.patient.security.PatientLoginDetails;
import com.spring.microservice.patient.security.PatientLogingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    @Autowired
    PatientLoginRepo patientLoginRepo;
    @Autowired
    PatientDetailsRepo patientDetailsRepo;
    @Autowired
    PatientLogingDetailsService patientLogingDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtills jwtUtills;

    @PostMapping("/add")
    public String addPatient(@RequestBody Patient patient){
        PatientLogin patientLogin=patient.getPatientLogin();
        PatientDetails patientDetails=patient.getPatientDetails();
        try{
            PatientLogin isPatientAvailabe=patientLoginRepo.findById(patientLogin.getEMAIL_ID()).get();
            if(isPatientAvailabe!=null){
                return "YOU ARE ALREADY REGISTERED. ";
            }
        }catch(Exception x){
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            String encryptPassword=bCryptPasswordEncoder.encode(patientLogin.getPASSWORD());
            patientLogin.setPASSWORD(encryptPassword);
            UUID id= UUID.randomUUID();
            patientDetails.setID(id.toString());
            patientDetails.setEMAIL_ID(patientLogin.getEMAIL_ID());
            patientDetails.setADDRESS(patientDetails.getADDRESS().replace(",","|"));
            patientLoginRepo.save(patientLogin);
            patientDetailsRepo.save(patientDetails);
            return "SUCCESSFULLY REGISTERED.";
        }

        return null;
    }

    @PostMapping("/authenticate")
    public String authenticatePatient(@RequestBody PatientLogin patientLogin){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(patientLogin.getEMAIL_ID(),patientLogin.getPASSWORD()));
        if(authentication.getName().equals(patientLogin.getEMAIL_ID())){
            PatientLoginDetails patientLoginDetails=(PatientLoginDetails) patientLogingDetailsService.loadUserByUsername(patientLogin.getEMAIL_ID());
            String TOKEN =jwtUtills.generateJwtToken(patientLoginDetails);
            return TOKEN;
        }
        return "AUTHENTICATION FAILED";
    }


    @GetMapping("/get/{email}")
    public Patient getPatient(@PathVariable("email") String email){
        PatientLogin patientLogin=patientLoginRepo.findById(email).get();
        PatientDetails patientDetails=patientDetailsRepo.findByEmail(email);
        patientDetails.setADDRESS(patientDetails.getADDRESS().replace("|",","));
        Patient patient=new Patient(patientDetails,patientLogin);
        return patient;

    }
    
    @GetMapping("/get/id/{id}")
    public PatientDetails getPatientById(@PathVariable("id") String id){
    	try {
    		PatientDetails patientDetails=patientDetailsRepo.findById(id).get();
            patientDetails.setADDRESS(patientDetails.getADDRESS().replace("|",","));
            return patientDetails;
    	}catch(Exception x) {
    		return null;
    	}
        

    }
    
    @GetMapping("/validade-token/{token}")
    public Boolean isTokenValid(@PathVariable("token") String token){
        try{
            String username=jwtUtills.getUsernameFromToken(token);
            PatientLoginDetails patientLoginDetails= (PatientLoginDetails) patientLogingDetailsService.loadUserByUsername(username);
            Boolean isTokenValid=jwtUtills.validateJwtToken(token,patientLoginDetails);
            return  isTokenValid;
        }catch(Exception x){
            return false;
        }

    }
    
    @PutMapping("/update")
    public String updatePatient(@RequestBody PatientDetails pd) {
    	try {
            pd.setADDRESS(pd.getADDRESS().replace(",","|"));
    		patientDetailsRepo.save(pd);
    		return "UPDATE SUCCESSFUL";
    	}catch(Exception x) {
    		return "UPDATE FAILED";
    	}
    	
    }

}
