package com.spring.microservice.patient.security;

import com.spring.microservice.patient.model.PatientLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PatientLoginDetails implements UserDetails {
    private PatientLogin patientLogin;

    public PatientLoginDetails(PatientLogin patientLogin) {
        this.patientLogin = patientLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("PATIENT"));
    }

    @Override
    public String getPassword() {
        return patientLogin.getPASSWORD();
    }

    @Override
    public String getUsername() {
        return patientLogin.getEMAIL_ID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
