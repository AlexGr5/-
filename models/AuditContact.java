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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "auditcontact")
@Data
public class AuditContact {
    @Id
    @Column(name = "PK_auditcontact")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_AuditContact;

    @Column(name = "userone")
    private String User;

    @Column(name = "dateedit")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private Date DateEdit;

    @Column(name = "typereqest")
    private String TypeReqest;

    @Column(name = "dateofconnect")
    private String DateOfConnect;

    @Column(name = "valuecontact")
    private String ValueContact;

    @ManyToOne
    @JoinColumn(name = "PK_Contact", referencedColumnName = "PK_Contact")
    private Contact contactAudit;
}
