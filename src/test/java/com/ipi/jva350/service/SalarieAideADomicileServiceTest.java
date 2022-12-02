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
    private SalarieAideADomicileService salarieAideADomicileService;

    @Test
    void clotureMoisJoursTravailles() throws SalarieException {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        double joursTravaillesInitial = 10;
        aide.setJoursTravaillesAnneeN(joursTravaillesInitial);
        aide.setMoisEnCours(LocalDate.parse("2022-11-01"));
        double joursTravailles = 20;
        // When
        salarieAideADomicileService.clotureMois(aide, joursTravailles);
        // Then
        // ou aide = repo.findByNom(...)
        Assertions.assertEquals(joursTravaillesInitial + joursTravailles,
                aide.getJoursTravaillesAnneeN());
    }

    @Test
    void clotureMoisCongesPayesAcquis() throws SalarieException {
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        double congesPayesAcquisInitial = 10;
        aide.setCongesPayesAcquisAnneeN(congesPayesAcquisInitial);
        aide.setMoisEnCours(LocalDate.parse("2022-11-01"));
        double joursTravailles = 20;
        // When
        salarieAideADomicileService.clotureMois(aide, joursTravailles);
        // Then
        // ou aide = repo.findByNom(...)
        Assertions.assertEquals(congesPayesAcquisInitial + SalarieAideADomicile.CONGES_PAYES_ACQUIS_PAR_MOIS,
                aide.getCongesPayesAcquisAnneeN());
    }

//    @Test
//    void calculeLimiteEntrepriseCongesPermis() {
//    }
}