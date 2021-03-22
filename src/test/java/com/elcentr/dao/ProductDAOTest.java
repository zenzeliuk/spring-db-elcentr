package com.elcentr.dao;

import com.elcentr.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    private Product product;

    @BeforeEach
    void init(){
        product = Product.builder()
                .name("test-name")
                .code("test-code")
                .amount(1)
                .timeRegistration(new Date().getTime())
                .build();
    }
    @AfterEach
    void deleteAll(){
        productDAO.deleteAll();
    }

    @Test
    void CRUDProduct(){
        Product savedProduct = productDAO.save(product);
        assertNotNull(savedProduct.getId());
        assertEquals(savedProduct, productDAO.findById(savedProduct.getId()).get());
        List<Product> productList = productDAO.findAll();
        assertTrue(productList.contains(savedProduct));
        productDAO.delete(savedProduct);
        productList = productDAO.findAll();
        assertFalse(productList.contains(savedProduct));
    }

    @Test
    void findByCode() {
        Product foundProductByCode = productDAO.findByCode(product.getCode());
        assertNull(foundProductByCode);
        Product savedProduct = productDAO.save(product);
        assertNotNull(savedProduct.getId());
        foundProductByCode = productDAO.findByCode(product.getCode());
        assertNotNull(foundProductByCode);
        assertEquals(product, foundProductByCode);
    }
}