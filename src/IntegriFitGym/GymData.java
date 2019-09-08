package IntegriFitGym;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class GymData implements Serializable {
    static final long  serialVersionUID = 4L;
    private ObjectOutputStream objOutStream;
    private String nationalId; // dont need this shit, just for verification
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int membershipDuration;
    private String memberAddress;
    private String medicalCondition;
    private String medicationName;
    private String doctorName;
    private String doctorNumber;

    public GymData(String nationalId, String firstName, String lastName, String phoneNumber, int membershipDuration, String memberAddress, String medicalCondition, String medicationName, String doctorName, String doctorNumber) {
        this.nationalId = nationalId; // Transient, therefore null
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.membershipDuration = membershipDuration;
        this.memberAddress = memberAddress;
        this.medicalCondition = medicalCondition;
        this.medicationName = medicationName;
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
    }

    public GymData(String nationalId, String firstName, String lastName, String phoneNumber) {
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(int membershipDuration) {
        this.membershipDuration = membershipDuration;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String toString() {
        return nationalId + " " + firstName + " " + lastName + " " + phoneNumber + " " +membershipDuration + " " + memberAddress + " " + medicalCondition + " " + medicationName + " " +doctorName + " " + doctorNumber;
    }
/*
    public Date getDateRegistered() {
        return dateRegistered;
    }
 
    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
*/

}