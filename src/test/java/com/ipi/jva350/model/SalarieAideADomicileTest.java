package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayesPlus10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(11);
        boolean aDroit = salarie.aLegalementDroitADesCongesPayes();
        Assertions.assertTrue(aDroit);
    }

    @Test
    void aPasLegalementDroitADesCongesPayesMoins10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(9);
        boolean aDroit = salarie.aLegalementDroitADesCongesPayes();
        Assertions.assertFalse(aDroit);
    }

    @Test
    void aLegalementDroitADesCongesPayesEgale10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(10);
        boolean aDroit = salarie.aLegalementDroitADesCongesPayes();
        Assertions.assertTrue(aDroit);
    }

    @ParameterizedTest
    @CsvSource({
            "'2022-11-01', '2022-11-10', 8"
    })
    void calculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, Integer expected) {
        LocalDate dateDebutParsed = LocalDate.parse(dateDebut);
        LocalDate dateFinParsed = LocalDate.parse(dateFin);
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LinkedHashSet<LocalDate> jourDeCongesDecompte = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebutParsed, dateFinParsed);
        Assertions.assertEquals(jourDeCongesDecompte.size(), expected);
    }
}