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
@Table(name = "Organization")
@Data
public class Organization {
    @Id
    @Column(name = "PK_Organization")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Organization;

    @Column(name = "nameorganization")
    private String NameOrganization;

    @Column(name = "Address")
    private String Address;

    @Column(name = "Email")
    private String Email;

    @OneToMany(mappedBy = "organizationSub")
    private List<Subdivision> subdivisions;

    @OneToMany(mappedBy = "organizationBran")
    private List<Branch> branches;
}
