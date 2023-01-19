package com.spring.microservice.patient.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientDetails {
    @Id
    private String ID;
    private String EMAIL_ID;
    private String MOBILE_NO;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String GENDER;
    private String ADDRESS;

    public PatientDetails() {
    }

    public PatientDetails(String ID, String EMAIL_ID, String MOBILE_NO, String FIRST_NAME, String LAST_NAME, String GENDER, String ADDRESS) {
        this.ID = ID;
        this.EMAIL_ID = EMAIL_ID;
        this.MOBILE_NO = MOBILE_NO;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.GENDER = GENDER;
        this.ADDRESS = ADDRESS;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(String EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    @Override
    public String toString() {
        return "PatientDetails{" +
                "ID='" + ID + '\'' +
                ", EMAIL_ID='" + EMAIL_ID + '\'' +
                ", MOBILE_NO='" + MOBILE_NO + '\'' +
                ", FIRST_NAME='" + FIRST_NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", GENDER='" + GENDER + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                '}';
    }
}
