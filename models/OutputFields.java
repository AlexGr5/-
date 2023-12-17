package com.example.web.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class OutputFields {

    private String firstName;

    private String surname;

    private String patronymic;

    private List<String> contactNumber;

    private List<String> nameJobTitle;

    private String nameOrganization;

    private String nameSubdivision;

    private String addressOrganization;

    private String emailSubdivision;

    private int numberOffice;

    private double sumRateHuman;

    public double getSumRateHuman() {
        return sumRateHuman;
    }

    public void setSumRateHuman(double sumRateHuman) {
        this.sumRateHuman = sumRateHuman;
    }

    public void setSumRateHuman() {
        this.sumRateHuman = 0;
    }

    public OutputFields() {
        this.firstName = "";
        this.surname = "";
        this.patronymic = "";
        this.nameOrganization = "";
        this.nameSubdivision = "";
        this.addressOrganization = "";
        this.emailSubdivision = "";
        this.numberOffice = 0;
        this.sumRateHuman = 0;
        this.contactNumber = new ArrayList<>();
        this.nameJobTitle = new ArrayList<>();
    }

    public void addContact(String contact)
    {
        contactNumber.add(contact);
    }

    public void addJobTitle(String jobTitle)
    {
        nameJobTitle.add(jobTitle);
    }

    public void deleteContact(String contact)
    {
        contactNumber.remove(contact);
    }

    public void deleteJobTitle(String jobTitle)
    {
        nameJobTitle.remove(jobTitle);
    }
}
