package com.marand.interview.controllers;

import com.marand.interview.Application;
import com.marand.interview.models.Disease;
import com.marand.interview.repositories.DiseaseRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DiseaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @After
    public void databaseDeleteAll() {
        diseaseRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
        Disease disease = new Disease("has_two_legs");
        mockMvc.perform(post("/v1/diseases").contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .content("<disease>has_two_legs</disease>"));

        List<Disease> diseases = diseaseRepository.findAll();
        assertThat(diseases).extracting(Disease::getDisease)
                .containsOnly("has_two_legs");
    }
}
