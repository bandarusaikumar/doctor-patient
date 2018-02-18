package com.marand.interview.repositories;

import com.marand.interview.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByFirstName(String firstName);
}
