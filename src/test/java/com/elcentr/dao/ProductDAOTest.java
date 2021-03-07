package com.elcentr.dao;

import com.elcentr.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    void findByCode() {
        Product product = Product.builder()
                .name("test-name")
                .code("test-code")
                .amount(1)
                .timeRegistration(new Date().getTime())
                .build();

        Product savedProduct = productDAO.save(product);
        Product foundProduct = productDAO.findByCode("test-code");

        assertNotNull(savedProduct);
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getCode(), foundProduct.getCode());
        productDAO.delete(savedProduct);
    }
}