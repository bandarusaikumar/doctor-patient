package com.marand.interview.services;

import com.marand.interview.models.Disease;
import com.marand.interview.models.Patient;

import java.util.List;

public interface PatientService {

    boolean exists(Patient patient);

    boolean hasDisease(Patient patient);

    Patient savePatient(Patient diseases);
}
