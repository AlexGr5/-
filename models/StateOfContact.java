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
@Table(name = "stateofcontact")
@Data
public class StateOfContact {
    @Id
    @Column(name = "PK_stateofcontact")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_StateOfContact;

    @Column(name = "valuecontactstate")
    private String ValueContactState;

    @OneToMany(mappedBy = "stateOfContact")
    private List<Contact> stateOfContacts;
}