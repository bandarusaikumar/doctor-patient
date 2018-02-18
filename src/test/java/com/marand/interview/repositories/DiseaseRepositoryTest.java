package com.marand.interview.repositories;

import com.marand.interview.models.Disease;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DiseaseRepositoryTest {

    public static final Logger logger = LoggerFactory.getLogger(DiseaseRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DiseaseRepository diseaseRepository;


    @Test
    public void whenFindById_thenReturnDisease() {
        logger.debug("Starting test whenFindById_thenReturnDisease().");

        // given
        Disease disease = new Disease("long_legs");
        entityManager.persist(disease);
        entityManager.flush();

        // when
        Disease returned = diseaseRepository.findByDisease(disease.getDisease());
        logger.debug("Found disease {}.", returned);

        // then
        assertThat(returned.getDisease())
                .isEqualTo(disease.getDisease());
    }
}
