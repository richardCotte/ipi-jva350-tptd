package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.h2.jdbc.JdbcSQLDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileRepositoryTest {


    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @BeforeEach
    void setUp() {
        salarieAideADomicileRepository.deleteAllInBatch();
    }

    @Test
    void findByNomNotFound() {
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom("Pierre");
        Assertions.assertEquals(null, res);
    }

    @Test
    void findByNomFound() {
        String testNom = "Pierre";
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setNom(testNom);
        salarieAideADomicileRepository.save(aide);
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom(testNom);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(testNom, res.getNom());
    }

    @ParameterizedTest
    @CsvSource({
            "90, 90, 1d",
            "80, 25, 3.2"
    })
    void partCongesPrisTotauxAnneeNMoins1(int congesPayesPrisAnneeNMoins1, int congesPayesAcquisAnneeNMoins1,
                                          double actualAvg) throws DataIntegrityViolationException {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesPrisAnneeNMoins1(congesPayesPrisAnneeNMoins1);
        salarie.setCongesPayesAcquisAnneeNMoins1(congesPayesAcquisAnneeNMoins1);
        salarieAideADomicileRepository.save(salarie);
        double expected = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        Assertions.assertEquals(expected, actualAvg);
    }
}