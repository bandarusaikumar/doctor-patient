package com.marand.interview.controllers;

import com.marand.interview.models.Disease;
import com.marand.interview.repositories.DiseaseRepository;
import com.marand.interview.services.DiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(value="diseases", description="Operations based on diseases")
public class DiseaseController {

    public static final Logger logger = LoggerFactory.getLogger(DiseaseController.class);

    private DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping(value = "/diseases", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "View a list of available diseases", response = List.class)
    public ResponseEntity<List<Disease>> getAllDiseases() {
        List<Disease> diseases = diseaseService.getAllDiseases();

        return new ResponseEntity<List<Disease>>(diseases, HttpStatus.OK);
    }

    @PostMapping(value = "/diseases", consumes = {MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Create a new disease", response = Disease.class)
    public Disease createDisease(@RequestBody Disease disease) {
        logger.info("Creating Disease : {}", disease);

        return diseaseService.saveDisease(disease);
    }
}
