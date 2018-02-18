package com.marand.interview.services;

import com.marand.interview.models.Disease;
import com.marand.interview.repositories.DiseaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DiseaseServiceImplTest {

    @TestConfiguration
    static class DiseaseServiceImplTestContextConfiguration {

        @Bean
        public DiseaseService diseaseService() {
            return new DiseaseServiceImpl();
        }
    }

    @Autowired
    private DiseaseService diseaseService;

    @MockBean
    private DiseaseRepository diseaseRepository;

    @Before
    public void setUp() {
        Disease disease = new Disease("white_hair");

        Mockito.when(diseaseRepository.findByDisease(disease.getDisease()))
                .thenReturn(disease);
    }

    @Test
    public void whenValidDisease_thenDiseaseShouldBeFound() {
        String disease = "white_hair";
        Disease returned = diseaseService.getDiseaseByValue(disease);

        assertThat(returned.getDisease())
                .isEqualTo(disease);
    }
}
