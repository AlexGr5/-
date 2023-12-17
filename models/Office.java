package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Office")
@Data
public class Office {
    @Id
    @Column(name = "PK_Office")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Office;

    @Column(name = "numberoffice")
    private int NumberOffice;

    @ManyToOne
    @JoinColumn(name = "PK_Subdivision", referencedColumnName = "PK_Subdivision")
    private Subdivision subdivisionOffice;

    @ManyToOne
    @JoinColumn(name = "PK_Human", referencedColumnName = "PK_Human")
    private Human humanOffice;
}
