package com.example.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity
//@Table(name = "Excursion")
public class Excursion {
    //@Id
    //@Column(name = "PK_Excursion")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PK_Excursion;

    //@Column(name = "nameexcurion")
    private String NameExcursion;

    //@Column(name = "Description")
    private String Description;

    //@ManyToOne
    //@JoinColumn(name = "PK_Entity", referencedColumnName = "PK_Entity")
    //private Place place;

    //@OneToMany(mappedBy = "excursion")
    //private List<Schedule> scheduleList;

    //@ManyToMany(mappedBy = "excursionsForGuid")
    //private List<Guide> guideList;

    //@OneToMany(mappedBy = "excursion2")
    //private List<GuideExcursion> guideExcursionList;

    public Excursion() {
    }

    /*
    public Excursion(String nameExcursion, String description, int  PK_Entity) {
        this.PK_Excursion = 0;
        NameExcursion = nameExcursion;
        Description = description;
        this.place = new Place(PK_Entity);
        this.scheduleList = new ArrayList<>();
        this.guideExcursionList = new ArrayList<>();
    }

    public Excursion(int PK_Excursion, String nameExcursion,
                     String description, Place place, List<Schedule> scheduleList, List<GuideExcursion> guideList) {
        this.PK_Excursion = PK_Excursion;
        NameExcursion = nameExcursion;
        Description = description;
        this.place = place;
        this.scheduleList = scheduleList;
        this.guideExcursionList = guideList;
    }

    public Excursion(int PK_Excursion, String nameExcursion, String description, Place place, List<Schedule> scheduleList) {
        this.PK_Excursion = PK_Excursion;
        NameExcursion = nameExcursion;
        Description = description;
        this.place = place;
        this.scheduleList = scheduleList;
    }

    public Excursion(int PK_Excursion, String nameExcursion, String description, Place place) {
        this.PK_Excursion = PK_Excursion;
        NameExcursion = nameExcursion;
        Description = description;
        this.place = place;
    }

    public int getPK_Excursion() {
        return PK_Excursion;
    }

    public void setPK_Excursion(int PK_Excursion) {
        this.PK_Excursion = PK_Excursion;
    }

    public String getNameExcursion() {
        return NameExcursion;
    }

    public void setNameExcursion(String nameExcursion) {
        NameExcursion = nameExcursion;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<GuideExcursion> getGuideList() {
        return guideExcursionList;
    }

    public void setGuideList(List<GuideExcursion> guideList) {
        this.guideExcursionList = guideList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursion excursion = (Excursion) o;
        return PK_Excursion == excursion.PK_Excursion && Objects.equals(NameExcursion, excursion.NameExcursion) && Objects.equals(Description, excursion.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PK_Excursion, NameExcursion, Description);
    }
     */
}
