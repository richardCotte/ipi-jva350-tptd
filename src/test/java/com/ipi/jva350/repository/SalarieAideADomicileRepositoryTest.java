package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileRepositoryTest {
    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Test
    void findByNom() {
        String name = "Paul";
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom(name);
        salarieAideADomicileRepository.save(salarie);
        SalarieAideADomicile foundSalarie = salarieAideADomicileRepository.findByNom(name);
        Assertions.assertEquals(salarie, foundSalarie);
    }
}