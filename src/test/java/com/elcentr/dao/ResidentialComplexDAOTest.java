package com.elcentr.dao;

import com.elcentr.model.ResidentialComplex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ResidentialComplexDAOTest {

    @Autowired
    private ResidentialComplexDAO residentialComplexDAO;

    private ResidentialComplex residentialComplex;

    @BeforeEach
    void init() {
        residentialComplex = ResidentialComplex.builder()
                .name("test-name")
                .address("test-address")
                .build();
    }
    @AfterEach
    void deleteAll(){
        residentialComplexDAO.deleteAll();
    }

    @Test
    void CRUDResidentialComplex() {
        ResidentialComplex savedResidentialComplex = residentialComplexDAO.save(residentialComplex);
        assertNotNull(savedResidentialComplex.getId());
        assertEquals(savedResidentialComplex, residentialComplexDAO.findById(savedResidentialComplex.getId()).get());
        List<ResidentialComplex> residentialComplexList = residentialComplexDAO.findAll();
        assertTrue(residentialComplexList.contains(savedResidentialComplex));
        residentialComplexDAO.delete(savedResidentialComplex);
        residentialComplexList = residentialComplexDAO.findAll();
        assertFalse(residentialComplexList.contains(savedResidentialComplex));
    }
}