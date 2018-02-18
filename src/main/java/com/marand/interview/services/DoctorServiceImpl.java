package com.marand.interview.services;

import com.marand.interview.models.Doctor;
import com.marand.interview.models.Patient;
import com.marand.interview.repositories.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    public static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DiseaseService diseaseService;


    @Override

    public boolean hasPatients(Doctor doctor) {
        return !doctor.getPatients().isEmpty();
    }

    @Override
    public List<Doctor> getAllDocs() {
        logger.debug("Returning list of all the doctors.");

        return doctorRepository.findAll();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        logger.debug("Save doctor with values {}.", doctor);
        List<Patient> patients;

        if(hasPatients(doctor)) {
            patients = doctor.getPatients();
            logger.debug("Doctor from {} department has {} number of patients.", doctor.getDepartment(), patients.size());
            for(Patient patient : patients) {
                logger.debug("Search for patient with {} id.", patient.getId());
                if(!patientService.exists(patient)) {
                    logger.debug("Patient {} {} dosen't exists. Saving", patient.getFirstName(), patient.getLastName());
                    patientService.savePatient(patient);
                }
            }
        }

        logger.debug("Doctor with {} ID from {} department was saved.", doctor.getId(), doctor.getDepartment());
        return this.doctorRepository.save(doctor);
    }


    private boolean existsDoctor(Long doctorId) {
        return this.doctorRepository.existsById(doctorId);
    }
}
