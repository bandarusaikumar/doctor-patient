package com.marand.interview.services;

import com.marand.interview.models.Disease;

import java.util.List;

public interface DiseaseService {

    boolean exists(Disease disease);

    Disease saveDisease(Disease diseases);

    Disease getDiseaseByValue(String disease);

    List<Disease> getAllDiseases();
}
