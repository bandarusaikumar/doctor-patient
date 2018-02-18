package com.marand.interview.controllers;

import com.marand.interview.models.Doctor;
import com.marand.interview.models.DocumentReport;
import com.marand.interview.repositories.DocumentReportRepository;
import com.marand.interview.services.DiseaseService;
import com.marand.interview.services.DoctorService;
import com.marand.interview.services.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(value="diseases", description="Operations based on doctor information")
public class DoctorController {

    public static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    private DoctorService doctorService;

    private PatientService patientService;

    private DiseaseService diseaseService;

    private DocumentReportRepository documentReportRepository;

    public DoctorController(DoctorService doctorService,
                            PatientService patientService,
                            DiseaseService diseaseService,
                            DocumentReportRepository documentReportRepository) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.diseaseService = diseaseService;
        this.documentReportRepository = documentReportRepository;
    }

    @GetMapping(value = "/doctors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "View a list of available doctors", response = List.class)
    public List<Doctor> getAllDoctors() {



        return doctorService.getAllDocs();
    }

    @PostMapping(value = "/doctors", produces = {MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Create a new doctor", response = Doctor.class)
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        documentReportLog(doctor.getId(), "ERr");

        return doctorService.saveDoctor(doctor);
    }


    private void documentReportLog(Long id, String errorMsg) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DocumentReport documentReport = new DocumentReport(timestamp, id, errorMsg);

        documentReportRepository.save(documentReport);
    }
}
