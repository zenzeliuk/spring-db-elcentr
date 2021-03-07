package com.elcentr.dao;

import com.elcentr.model.Enclosure;
import com.elcentr.model.Product;
import com.elcentr.model.ProductEnclosure;
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


    @Test
    void findAllByProduct() {
        Product product = Product.builder()
                .name("test-name")
                .code("test-code")
                .amount(1)
                .timeRegistration(new Date().getTime())
                .build();
        Product savedProduct = productDAO.save(product);
        assertNotNull(savedProduct);

        Enclosure enclosure = Enclosure.builder()
                .name("test-name")
                .build();
        Enclosure savedEnclosure = enclosureDAO.save(enclosure);
        assertNotNull(savedEnclosure);

        ProductEnclosure productEnclosure = ProductEnclosure.builder()
                .product(savedProduct)
                .enclosure(savedEnclosure)
                .amountEnclosure(1)
                .build();
        ProductEnclosure savedProductEnclosure = productEnclosureDAO.save(productEnclosure);
        assertNotNull(savedProductEnclosure);

        List<ProductEnclosure> foundByProduct = productEnclosureDAO.findAllByProduct(savedProduct);

        assertEquals(foundByProduct.size(), 1);
        assertEquals(savedProductEnclosure, foundByProduct.get(0));

        productEnclosureDAO.delete(savedProductEnclosure);
        enclosureDAO.delete(savedEnclosure);
        productDAO.delete(savedProduct);
    }

    @Test
    void findAllByEnclosure() {
    }
}