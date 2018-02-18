package com.marand.interview.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="disease")
public class Disease {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The auto-generated disease ID")
    private Long id;

    @JacksonXmlText
    @ApiModelProperty(notes = "Which kind of a disease")
    private String disease;

    public Disease() {}

    public Disease(String disease) {
        this.disease = disease;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "disease: {" +
                "id=" + id +
                ", disease='" + disease + '\'' +
                '}';
    }
}
