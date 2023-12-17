package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Rate")
@Data
public class Rate {
    @Id
    @Column(name = "PK_Rate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Rate;

    @Column(name = "ratevalue")
    private double RateValue;

    @Column(name = "dateofemployment")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private Date DateOfEmployment;

    @Column(name = "dateofdismissal")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private Date DateOfDismissal;

    @ManyToOne
    @JoinColumn(name = "PK_Subdivision", referencedColumnName = "PK_Subdivision")
    private Subdivision subdivisionRate;

    @ManyToOne
    @JoinColumn(name = "PK_jobtitle", referencedColumnName = "PK_jobtitle")
    private JobTitle jobTitleRate;

    @ManyToOne
    @JoinColumn(name = "PK_Human", referencedColumnName = "PK_Human")
    private Human humanRate;
}
