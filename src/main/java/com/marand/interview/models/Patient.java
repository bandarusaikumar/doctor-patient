package com.marand.interview.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patient")
@JsonPropertyOrder({"id", "first_name", "last_name", "diseases"})
public class Patient {

    @Id
    @ApiModelProperty(notes = "The auto-generated disease ID")
    private Long id;

    @Column(name = "first_name")
    @JacksonXmlProperty(localName = "first_name")
    @ApiModelProperty(notes = "First name of the patient")
    private String firstName;

    @Column(name = "last_name")
    @JacksonXmlProperty(localName = "last_name")
    @ApiModelProperty(notes = "Last name of the patient")
    private String lastName;


    @ManyToMany(targetEntity=Disease.class)
    @JacksonXmlProperty(localName = "disease")
    @JacksonXmlElementWrapper(localName = "diseases")
    @ApiModelProperty(notes = "List of all diseases which the patient has")
    private List<Disease> diseases;

    public Patient() {}

    public Patient(Long id, String firstName, String lastName, List<Disease> diseases) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diseases = diseases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    @Override
    public String toString() {
        return "patient: {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", diseases=" + diseases +
                '}';
    }
}
