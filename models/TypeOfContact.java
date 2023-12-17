package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "typeofcontact")
@Data
public class TypeOfContact {
    @Id
    @Column(name = "PK_typeofcontact")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_TypeOfContact;

    @Column(name = "valuetupecontact")
    private String ValueTupeContact;

    @OneToMany(mappedBy = "typeOfContact")
    private List<Contact> typeOfContacts;
}