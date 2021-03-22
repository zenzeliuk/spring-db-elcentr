package com.elcentr.dao;

import com.elcentr.model.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnclosureDAOTest {

    @Autowired
    private EnclosureDAO enclosureDAO;

    private Enclosure enclosure;

    @BeforeEach
    void init() {
        enclosure = Enclosure.builder()
                .name("test-name")
                .build();
    }
    @AfterEach
    void deleteAll(){
        enclosureDAO.deleteAll();
    }

    @Test
    void CRUDEnclosure() {
        Enclosure savedEnclosure = enclosureDAO.save(enclosure);
        assertNotNull(savedEnclosure.getId());
        assertEquals(savedEnclosure, enclosureDAO.findById(savedEnclosure.getId()).get());
        List<Enclosure> enclosureList = enclosureDAO.findAll();
        assertTrue(enclosureList.contains(savedEnclosure));
        enclosureDAO.delete(savedEnclosure);
        enclosureList = enclosureDAO.findAll();
        assertFalse(enclosureList.contains(savedEnclosure));
    }
}