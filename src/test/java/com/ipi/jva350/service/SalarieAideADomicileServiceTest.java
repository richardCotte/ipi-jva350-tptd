package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.crypto.Data;
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

    @Test
    void calculeLimiteEntrepriseCongesPermis() throws SalarieException {

        SalarieAideADomicile sd = new SalarieAideADomicile();
        sd.setNom("Joris");
        sd.setMoisEnCours(LocalDate.of(2022, 5, 1));
        sd.setCongesPayesAcquisAnneeNMoins1(SalarieAideADomicile.CONGES_PAYES_ACQUIS_PAR_MOIS * 12);
        sd.setMoisDebutContrat(LocalDate.of(2022, 01, 01));
        salarieAideADomicileService.creerSalarieAideADomicile(sd);
        long result = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(sd.getMoisEnCours(),
                sd.getCongesPayesAcquisAnneeNMoins1(),
                sd.getMoisDebutContrat(),
                LocalDate.of(2022, 7, 1),
                LocalDate.of(2022, 7, 8));

        Assertions.assertEquals(8l, result);
    }
}