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
@Table(name = "Branch")
@Data
public class Branch {
    @Id
    @Column(name = "PK_Branch")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Branch;

    @Column(name = "Address")
    private String Address;

    @ManyToOne
    @JoinColumn(name = "PK_Organization", referencedColumnName = "PK_Organization")
    private Organization organizationBran;

    @OneToMany(mappedBy = "branchSub")
    private List<Subdivision> subdivisions;
}
