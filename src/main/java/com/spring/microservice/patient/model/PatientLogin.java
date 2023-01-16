package com.spring.microservice.patient.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientLogin {
    @Id
    private String EMAIL_ID;
    private String PASSWORD;

    public PatientLogin() {
    }

    public PatientLogin(String EMAIL_ID, String PASSWORD) {
        this.EMAIL_ID = EMAIL_ID;
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(String EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @Override
    public String toString() {
        return "PatientLogin{" +
                "EMAIL_ID='" + EMAIL_ID + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }
}
