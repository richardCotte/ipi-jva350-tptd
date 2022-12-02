package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class SalarieAideADomicileTest {

    @Test
    void testALegalementDroitADesCongesPayesDefault() {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        // When
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertFalse(res);
    }

    @Test
    void testALegalementDroitADesCongesPayes9() {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setJoursTravaillesAnneeNMoins1(9);
        // When
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertFalse(res);
    }

    @Test
    void testALegalementDroitADesCongesPayes10() {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setJoursTravaillesAnneeNMoins1(10);
        // When
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertTrue(res);
    }

    @Test
    void testALegalementDroitADesCongesPayesBigNumber() {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setJoursTravaillesAnneeNMoins1(100);
        // When
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertTrue(res);
    }

    @ParameterizedTest
    @CsvSource({
            "'2022-10-31', '2022-10-31', 1",
            "'2022-10-31', '2022-11-02', 2",
            "'2022-10-31', '2022-11-03', 3",
            "'2022-10-31', '2022-11-04', 5",
            "'2022-10-28', '2022-10-30', 2",
            "'2022-10-28', '2022-10-31', 3"
    })
    void testCalculeJoursDeCongeDecomptesPourPlage(String stringDateDebut, String stringDateFin, int expectedSize) {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        // When
        LinkedHashSet<LocalDate> joursDeCongesDecomptes = aide
                .calculeJoursDeCongeDecomptesPourPlage(LocalDate.parse(stringDateDebut), LocalDate.parse(stringDateFin));
        // Then
        Assertions.assertEquals(joursDeCongesDecomptes.size(), expectedSize);
    }

}
