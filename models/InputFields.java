package com.example.web.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class InputFields {

    private String firstName;

    private String surname;

    private String patronymic;

    private String contactNumber;

    private String nameJobTitle;

    private String nameOrganization;

    private String nameSubdivision;

    private String addressOrganization;

    private String emailSubdivision;

    private int numberOffice;

    public InputFields() {
        this.firstName = "";
        this.surname = "";
        this.patronymic = "";
        this.contactNumber = "";
        this.nameJobTitle = "";
        this.nameOrganization = "";
        this.nameSubdivision = "";
        this.addressOrganization = "";
        this.emailSubdivision = "";
        this.numberOffice = 0;
    }

    @Override
    public String toString() {
        return "InputFields{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", nameJobTitle='" + nameJobTitle + '\'' +
                ", nameOrganization='" + nameOrganization + '\'' +
                ", nameSubdivision='" + nameSubdivision + '\'' +
                ", addressOrganization='" + addressOrganization + '\'' +
                ", emailSubdivision='" + emailSubdivision + '\'' +
                ", numberOffice=" + numberOffice +
                '}';
    }
}
