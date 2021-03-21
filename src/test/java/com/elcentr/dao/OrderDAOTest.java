package com.elcentr.dao;

import com.elcentr.model.Order;
import com.elcentr.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderDAOTest {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ProductDAO productDAO;

//    @Test
//    void findByProduct() {
//        Product product = Product.builder()
//                .name("test-name")
//                .code("test-code")
//                .amount(1)
//                .timeRegistration(new Date().getTime())
//                .build();
//
//        Product savedProduct = productDAO.save(product);
//
//        Order order = Order.builder()
//                .product(savedProduct)
//                .build();
//
//        Order savedOrder = orderDAO.save(order);
//
//        Order orderByProduct = orderDAO.findByProduct(savedProduct);
//
//        assertEquals(savedProduct, orderByProduct.getProduct());
//
//        orderDAO.delete(savedOrder);
//        productDAO.delete(savedProduct);
//
//    }
}
