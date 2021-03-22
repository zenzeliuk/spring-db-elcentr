package com.elcentr.dao;

import com.elcentr.model.Enclosure;
import com.elcentr.model.Product;
import com.elcentr.model.ProductEnclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductEnclosureDAOTest {

    @Autowired
    private ProductEnclosureDAO productEnclosureDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private EnclosureDAO enclosureDAO;

    private Product product;
    private Enclosure enclosure;

    @BeforeEach
    void init(){
        product = Product.builder()
                .name("test-name")
                .code("test-code")
                .amount(1)
                .timeRegistration(new Date().getTime())
                .build();

        enclosure = Enclosure.builder()
                .name("test-name")
                .build();
    }
    @AfterEach
    void deleteAll(){
        productEnclosureDAO.deleteAll();
        productDAO.deleteAll();
        enclosureDAO.deleteAll();
    }

    @Test
    void CRUDProductEnclosure(){
        Product savedProduct = productDAO.save(product);
        Enclosure savedEnclosure = enclosureDAO.save(enclosure);
        ProductEnclosure productEnclosure = ProductEnclosure.builder()
                .product(savedProduct)
                .enclosure(savedEnclosure)
                .amountEnclosure(1)
                .build();
        ProductEnclosure savedProductEnclosure = productEnclosureDAO.save(productEnclosure);

        assertNotNull(savedProduct.getId());
        assertNotNull(savedEnclosure.getId());
        assertNotNull(savedProductEnclosure.getId());

        assertEquals(savedProductEnclosure, productEnclosureDAO.findById(savedProductEnclosure.getId()).get());
        List<ProductEnclosure> productEnclosureList = productEnclosureDAO.findAll();
        assertTrue(productEnclosureList.contains(savedProductEnclosure));
        productEnclosureDAO.delete(savedProductEnclosure);
        productEnclosureList = productEnclosureDAO.findAll();
        assertFalse(productEnclosureList.contains(savedProductEnclosure));
    }

    @Test
    void findAllByProductId(){
        Product savedProduct = productDAO.save(product);
        Enclosure savedEnclosure = enclosureDAO.save(enclosure);
        ProductEnclosure productEnclosure = ProductEnclosure.builder()
                .product(savedProduct)
                .enclosure(savedEnclosure)
                .amountEnclosure(1)
                .build();

        assertNotNull(savedProduct.getId());
        List<ProductEnclosure> productEnclosureListByProductId = productEnclosureDAO.findAllByProductId(savedProduct.getId());
        assertTrue(productEnclosureListByProductId.isEmpty());

        ProductEnclosure savedProductEnclosure = productEnclosureDAO.save(productEnclosure);

        productEnclosureListByProductId = productEnclosureDAO.findAllByProductId(savedProduct.getId());
        assertFalse(productEnclosureListByProductId.isEmpty());
        assertEquals(savedProductEnclosure, productEnclosureListByProductId.get(0));
    }

    @Test
    void findAllByEnclosureId(){
        Product savedProduct = productDAO.save(product);
        Enclosure savedEnclosure = enclosureDAO.save(enclosure);
        ProductEnclosure productEnclosure = ProductEnclosure.builder()
                .product(savedProduct)
                .enclosure(savedEnclosure)
                .amountEnclosure(1)
                .build();

        assertNotNull(savedEnclosure.getId());
        List<ProductEnclosure> productEnclosureListByEnclosureId = productEnclosureDAO.findAllByEnclosureId(savedEnclosure.getId());
        assertTrue(productEnclosureListByEnclosureId.isEmpty());

        ProductEnclosure savedProductEnclosure = productEnclosureDAO.save(productEnclosure);

        productEnclosureListByEnclosureId = productEnclosureDAO.findAllByEnclosureId(savedEnclosure.getId());
        assertFalse(productEnclosureListByEnclosureId.isEmpty());
        assertEquals(savedProductEnclosure, productEnclosureListByEnclosureId.get(0));
    }
}