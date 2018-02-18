package com.marand.interview.controllers;

import com.marand.interview.models.Patient;
import com.marand.interview.repositories.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(value="diseases", description="Operations based on patients")
public class PatientController {

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping(value = "/patients", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "View a list of available patients", response = List.class)
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    @PostMapping(value = "/patients", consumes = {MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Create a new patient", response = Patient.class)
    public Patient createPatient(@RequestBody Patient patient) {
        logger.info("Creating Patient : {}", patient);

        return patientRepository.save(patient);
    }
}
