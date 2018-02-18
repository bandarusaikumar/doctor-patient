package com.marand.interview.repositories;

import com.marand.interview.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByDepartment(String department);
}
