package com.marand.interview.services;

import com.marand.interview.models.Disease;
import com.marand.interview.repositories.DiseaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    public static final Logger logger = LoggerFactory.getLogger(DiseaseServiceImpl.class);

    @Autowired
    private DiseaseRepository diseaseRepository;


    @Override
    public boolean exists(Disease disease) {
        return false;
    }

    @Override
    public Disease saveDisease(Disease disease) {
        logger.info("Disease called {} was saved.", disease.getDisease());

        Disease found = this.diseaseRepository.findByDisease(disease.getDisease());
        if(found != null) {
            logger.info("Disease called {} was already in DB under {} id.", found.getDisease(), found.getId());
            return found;
        }

        return this.diseaseRepository.save(disease);
    }

    @Override
    public List<Disease> getAllDiseases() {
        logger.debug("Returning list of all the diseases.");

        return diseaseRepository.findAll();
    }

    public Disease getDiseaseByValue(String disease) {
        return this.diseaseRepository.findByDisease(disease);
    }
}
