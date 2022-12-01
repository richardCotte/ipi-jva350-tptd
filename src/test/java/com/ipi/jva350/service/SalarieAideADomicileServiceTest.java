package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileServiceTest {

    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @Test
    void clotureMoisJoursTravailles() throws SalarieException {
        double joursTravaillesInitial = 10;
        double joursTravailles = 20;
        LocalDate currentDate = LocalDate.now();

        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile();
        salarieAideADomicile.setJoursTravaillesAnneeN(joursTravaillesInitial);
        salarieAideADomicile.setMoisEnCours(currentDate);


        salarieAideADomicileService.clotureMois(salarieAideADomicile, joursTravailles);
        Assertions.assertEquals(joursTravaillesInitial + joursTravailles, salarieAideADomicile.getJoursTravaillesAnneeN());
    }

    @Test
    void clotureMoisCongesPayes() throws SalarieException {
        double congesPayes = 10;
        double joursTravailles = 20;
        LocalDate currentDate = LocalDate.now();

        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile();
        salarieAideADomicile.setCongesPayesAcquisAnneeN(congesPayes);
        salarieAideADomicile.setMoisEnCours(currentDate);

        salarieAideADomicileService.clotureMois(salarieAideADomicile, joursTravailles);
        Assertions.assertEquals(congesPayes + SalarieAideADomicile.CONGES_PAYES_ACQUIS_PAR_MOIS, salarieAideADomicile.getCongesPayesAcquisAnneeN());
    }

//    @BeforeEach
//    public void setUp() {
//
//    }
//
//
//    @ParameterizedTest
//    @CsvSource({
//
//    })
//    void clotureMois(String name, LocalDate moisDebutContrat, LocalDate moisEnCours,
//                     double joursTravaillesAnneeN, double congesPayesAcquisAnneeN,
//                     double joursTravaillesAnneeNMoins1, double congesPayesAcquisAnneeNMoins1,
//                     double congesPayesPrisAnneeNMoins1,
//                     double joursTravailles,
//                     String expectedName, LocalDate expectedMoisDebutContrat, LocalDate expectedMoisEnCours,
//                     double expectedJoursTravaillesAnneeN, double expectedCongesPayesAcquisAnneeN,
//                     double expectedJoursTravaillesAnneeNMoins1, double expectedCongesPayesAcquisAnneeNMoins1,
//                     double expectedCongesPayesPrisAnneeNMoins1) throws SalarieException {
//
//        SalarieAideADomicile salarie = new SalarieAideADomicile(name, moisDebutContrat, moisEnCours, joursTravaillesAnneeN,
//                congesPayesAcquisAnneeN, joursTravaillesAnneeNMoins1, congesPayesAcquisAnneeNMoins1, congesPayesPrisAnneeNMoins1);
//
//        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
//
//        salarieAideADomicileService.clotureMois(salarie, joursTravailles);
//
//        SalarieAideADomicile expectedSalarie = new SalarieAideADomicile(expectedName, expectedMoisDebutContrat, expectedMoisEnCours, expectedJoursTravaillesAnneeN,
//                expectedCongesPayesAcquisAnneeN, expectedJoursTravaillesAnneeNMoins1, expectedCongesPayesAcquisAnneeNMoins1, expectedCongesPayesPrisAnneeNMoins1);
//
//        Assertions.assertEquals(salarie, expectedSalarie);
//
//    }
}