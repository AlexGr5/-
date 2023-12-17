package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Subdivision")
@Data
public class Subdivision {
    @Id
    @Column(name = "PK_Subdivision")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Subdivision;

    @Column(name = "namesubdivision")
    private String NameSubdivision;

    @Column(name = "kodsubdivision")
    private String KodSubdivision;

    @Column(name = "abbreviation")
    private String Abbreviation;

    @Column(name = "Email")
    private String Email;

    @ManyToOne
    @JoinColumn(name = "PK_Organization", referencedColumnName = "PK_Organization")
    private Organization organizationSub;

    @ManyToOne
    @JoinColumn(name = "PK_Branch", referencedColumnName = "PK_Branch")
    private Branch branchSub;

    @OneToMany(mappedBy = "subdivisionOne")
    private List<Subdivision> subsubdivisions;

    @ManyToOne
    @JoinColumn(name = "PK_subdivision_two", referencedColumnName = "PK_Subdivision")
    private Subdivision subdivisionOne;

    @OneToMany(mappedBy = "subdivisionRate")
    private List<Rate> rates;

    @OneToMany(mappedBy = "subdivisionOffice")
    private List<Office> offices;
}
