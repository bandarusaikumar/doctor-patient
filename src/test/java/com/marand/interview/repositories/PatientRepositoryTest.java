package com.marand.interview.repositories;

import com.marand.interview.models.Patient;
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
public class PatientRepositoryTest {

    public static final Logger logger = LoggerFactory.getLogger(PatientRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void whenFindByFirstName_thenReturnPatient() {
        logger.debug("Starting test whenFindById_thenReturnPatient().");

        // given
        Patient patient = new Patient(new Long("2"), "Bostjan", "Lah", null);
        entityManager.persist(patient);
        entityManager.flush();

        // when
        Patient returned = patientRepository.findByFirstName(patient.getFirstName());
        logger.debug("Found patient {}.", returned);

        // then
        assertThat(returned.getFirstName())
                .isEqualTo(patient.getFirstName());
    }
}
