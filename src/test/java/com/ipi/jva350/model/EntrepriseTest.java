package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class EntrepriseTest {

    @ParameterizedTest
    @CsvSource({
            "'2022-12-02', '2022-12-01', '2022-12-03', true",
            "'2022-12-24', '2022-12-01', '2022-12-03', false",
            "'2022-11-01', '2022-12-01', '2022-12-03', false",
            "'2022-12-01', '2022-12-01', '2022-12-03', true",
            "'2022-12-03', '2022-12-01', '2022-12-03', true"
    })
    void estDansPlage(String testedDateStr, String dateDebutStr, String dateFinStr, boolean actual) {
        LocalDate testedDate = LocalDate.parse(testedDateStr);
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);
        LocalDate dateFin = LocalDate.parse(dateFinStr);

        boolean expected = Entreprise.estDansPlage(testedDate, dateDebut, dateFin);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource ({
            "'2022-12-25', true",
            "'2022-12-01', false",
            "'2022-08-15', true",
            "'2022-05-01', true",
            "'2022-06-09', false"
    })
    void estJourFerie(String dateStr, boolean actual) {

        LocalDate date = LocalDate.parse(dateStr);

        Assertions.assertEquals(Entreprise.estJourFerie(date), actual);
    }
}