package com.marand.interview.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="doctor")
@JacksonXmlRootElement(localName = "doctor")
public class Doctor {

    @Id
    @ApiModelProperty(notes = "The auto-generated disease ID")
    private Long id;

    @JsonIgnore
    @ApiModelProperty(notes = "Name of the doctor")
    private String name;

    @ApiModelProperty(notes = "Department in which the doctor works")
    private String department;

    @OneToMany
    @JacksonXmlProperty(localName = "patient")
    @JacksonXmlElementWrapper(localName = "patients")
    @ApiModelProperty(notes = "List of all the patients that are assigned to the doctor")
    private List<Patient> patients;

    public Doctor() {}

    public Doctor(Long id, String name, String department, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.patients = patients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "doctor: {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", patients=" + patients +
                '}';
    }
}
