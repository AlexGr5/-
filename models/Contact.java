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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Contact")
@Data
public class Contact {
    @Id
    @Column(name = "PK_Contact")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Contact;

    @Column(name = "valuecontact")
    private String ValueContact;

    @Column(name = "dateofconnect")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private Date DateOfConnect;

    @ManyToOne
    @JoinColumn(name = "PK_Human", referencedColumnName = "PK_Human")
    private Human humanContact;

    @OneToMany(mappedBy = "contactAudit")
    private List<AuditContact> auditContacts;

    @ManyToOne
    @JoinColumn(name = "PK_typeofcontact", referencedColumnName = "PK_typeofcontact")
    private TypeOfContact typeOfContact;

    @ManyToOne
    @JoinColumn(name = "PK_stateofcontact", referencedColumnName = "PK_stateofcontact")
    private StateOfContact stateOfContact;
}
