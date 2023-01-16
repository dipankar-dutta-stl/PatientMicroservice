package com.spring.microservice.patient.model;

public class Patient {
    private PatientDetails patientDetails;
    private PatientLogin patientLogin;

    public Patient(PatientDetails patientDetails, PatientLogin patientLogin) {
        this.patientDetails = patientDetails;
        this.patientLogin = patientLogin;
    }

    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    public PatientLogin getPatientLogin() {
        return patientLogin;
    }

    public void setPatientLogin(PatientLogin patientLogin) {
        this.patientLogin = patientLogin;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientDetails=" + patientDetails +
                ", patientLogin=" + patientLogin +
                '}';
    }
}
