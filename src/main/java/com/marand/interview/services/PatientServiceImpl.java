package com.marand.interview.services;

import com.marand.interview.models.Disease;
import com.marand.interview.models.Patient;
import com.marand.interview.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    public static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiseaseService diseaseService;

    public PatientServiceImpl(PatientRepository patientRepository,
                              DiseaseService diseaseService) {
        this.patientRepository = patientRepository;
        this.diseaseService = diseaseService;
    }

    @Override
    public boolean exists(Patient patient) {
        return false;
    }

    @Override
    public boolean hasDisease(Patient patient) {
        return !patient.getDiseases().isEmpty();
    }

    @Override
    public Patient savePatient(Patient patient) {
        logger.info("Save patient with values {}.", patient);
        List<Disease> diseases;
        List<Disease> savedDiseases = new ArrayList<>();

        if(hasDisease(patient)) {
            diseases = patient.getDiseases();
            logger.info("Patient {} has {} number of diseases", patient.getFirstName(), diseases.size());
            for(Disease disease : diseases) {
                logger.info("Search for disease with {} id.", disease.getId());
                if(!diseaseService.exists(disease)) {
                    logger.info("Disease {} dosen't exists. Saving", disease.getDisease());
                    savedDiseases.add(diseaseService.saveDisease(disease));
                }
            }
        }

        patient.setDiseases(savedDiseases);
        logger.info("Patient with {} ID named {} {} was saved.", patient.getId(), patient.getFirstName(), patient.getLastName());
        logger.info("Patient with {} ID has following diseases {}.", patient.getId(), patient.getDiseases());
        return this.patientRepository.save(patient);
    }

    private boolean existsPatient(Long patientId) {
        return this.patientRepository.existsById(patientId);
    }
}
