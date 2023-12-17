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
@Table(name = "jobtitle")
@Data
public class JobTitle {
    @Id
    @Column(name = "PK_jobtitle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_JobTitle;

    @Column(name = "nameofjobtitle")
    private String NameOfJobTitle;

    @OneToMany(mappedBy = "jobTitleRate")
    private List<Rate> rates;
}
