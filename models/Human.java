package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Human")
@Data
public class Human {
    @Id
    @Column(name = "PK_Human")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Human;

    @Column(name = "namehuman")
    private String NameHuman;

    @Column(name = "surname")
    private String Surname;

    @Column(name = "Patronymic")
    private String Patronymic;

    @Column(name = "dateofbirth")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private Date DateOfBirth;

    @Column(name = "Email")
    private String Email;

    @OneToMany(mappedBy = "humanRate")
    private List<Rate> rates;

    @OneToMany(mappedBy = "humanOffice")
    private List<Office> offices;

    @OneToMany(mappedBy = "humanContact")
    private List<Contact> contacts;
}
