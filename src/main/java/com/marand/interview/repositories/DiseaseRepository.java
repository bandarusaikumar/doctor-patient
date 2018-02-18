package com.marand.interview.repositories;

import com.marand.interview.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Disease findByDisease(String disease);
}
