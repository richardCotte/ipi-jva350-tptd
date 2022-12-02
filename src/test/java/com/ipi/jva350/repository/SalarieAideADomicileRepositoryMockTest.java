package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileRepositoryMockTest {
    @Mock
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
        // Given
        String testNom = "Pierre";
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setNom(testNom);
        Mockito.when(salarieAideADomicileRepository.findByNom(testNom)).thenReturn(aide);

        // When
        salarieAideADomicileRepository.save(aide);
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom(testNom);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(testNom, res.getNom());
    }
}