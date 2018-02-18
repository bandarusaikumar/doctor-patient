package com.marand.interview.repositories;

import com.marand.interview.models.DocumentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentReportRepository extends JpaRepository<DocumentReport, Long> {
}
