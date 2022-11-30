package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayesPlus10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(15);
        Assertions.assertTrue(salarie.aLegalementDroitADesCongesPayes());
    }
    
    @Test
    void aPasLegalementDroitADesCongesPayesMoins10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(9);
        Assertions.assertFalse(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void aLegalementDroitADesCongesPayesEgale10() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(10);
        Assertions.assertTrue(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void calculeJoursDeCongeDecomptesPourPlage() {
    }
}