package com.marand.interview.services;

import com.marand.interview.models.Doctor;

import java.util.List;

public interface DoctorService {

    boolean hasPatients(Doctor doctor);

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> getAllDocs();
}
