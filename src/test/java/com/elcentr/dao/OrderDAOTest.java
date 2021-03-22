package com.elcentr.dao;

import com.elcentr.model.Customer;
import com.elcentr.model.Order;
import com.elcentr.model.Product;
import com.elcentr.model.ResidentialComplex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDAOTest {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ResidentialComplexDAO residentialComplexDAO;
    @Autowired
    private CustomerDAO customerDAO;

    private Customer customer;
    private Product product;
    private ResidentialComplex residentialComplex;

    @BeforeEach
    void init(){
         customer = Customer.builder()
                .name("test-name")
                .notes("test-notes")
                .build();

         product = Product.builder()
                .name("test-name")
                .code("test-code")
                .amount(1)
                .timeRegistration(new Date().getTime())
                .build();

         residentialComplex = ResidentialComplex.builder()
                .name("test-name")
                .address("test-address")
                .build();
    }
    @AfterEach
    void deleteAll(){
        orderDAO.deleteAll();
        customerDAO.deleteAll();
        productDAO.deleteAll();
        residentialComplexDAO.deleteAll();
    }

    @Test
    void CRUDOrder() {
        Customer savedCustomer = customerDAO.save(customer);
        Product savedProduct = productDAO.save(product);
        ResidentialComplex savedResidentialComplex = residentialComplexDAO.save(residentialComplex);
        Order order = Order.builder()
                .customer(savedCustomer)
                .product(savedProduct)
                .residentialComplex(savedResidentialComplex)
                .build();
        Order savedOrder = orderDAO.save(order);

        assertNotNull(savedCustomer.getId());
        assertNotNull(savedProduct.getId());
        assertNotNull(savedResidentialComplex.getId());
        assertNotNull(savedOrder.getId());

        assertEquals(savedOrder, orderDAO.findById(savedOrder.getId()).get());
        List<Order> orderList = orderDAO.findAll();
        assertTrue(orderList.contains(savedOrder));
        orderDAO.delete(savedOrder);
        orderList = orderDAO.findAll();
        assertFalse(orderList.contains(savedOrder));
   }

    @Test
    void findByProductId() {
        Customer savedCustomer = customerDAO.save(customer);
        Product savedProduct = productDAO.save(product);
        ResidentialComplex savedResidentialComplex = residentialComplexDAO.save(residentialComplex);
        Order order = Order.builder()
                .customer(savedCustomer)
                .product(savedProduct)
                .residentialComplex(savedResidentialComplex)
                .build();
        Order savedOrder = orderDAO.save(order);
        Order foundOrderByIdProduct = orderDAO.findByProductId(savedProduct.getId());
        assertNotNull(savedOrder.getId());
        assertNotNull(foundOrderByIdProduct.getId());
        assertEquals(savedOrder, foundOrderByIdProduct);
    }

    @Test
    void findAllByCustomerId() {
        Customer savedCustomer = customerDAO.save(customer);
        Product savedProduct = productDAO.save(product);
        ResidentialComplex savedResidentialComplex = residentialComplexDAO.save(residentialComplex);
        Order order = Order.builder()
                .customer(savedCustomer)
                .product(savedProduct)
                .residentialComplex(savedResidentialComplex)
                .build();

        assertNotNull(savedCustomer.getId());
        List<Order> orderListByCustomerId = orderDAO.findAllByCustomerId(savedCustomer.getId());
        assertTrue(orderListByCustomerId.isEmpty());

        Order savedOrder = orderDAO.save(order);

        orderListByCustomerId = orderDAO.findAllByCustomerId(savedCustomer.getId());
        assertFalse(orderListByCustomerId.isEmpty());
        assertEquals(savedOrder, orderListByCustomerId.get(0));
    }

    @Test
    void findAllByResidentialComplexId() {
        Customer savedCustomer = customerDAO.save(customer);
        Product savedProduct = productDAO.save(product);
        ResidentialComplex savedResidentialComplex = residentialComplexDAO.save(residentialComplex);
        Order order = Order.builder()
                .customer(savedCustomer)
                .product(savedProduct)
                .residentialComplex(savedResidentialComplex)
                .build();

        assertNotNull(savedResidentialComplex.getId());
        List<Order> orderListByResidentialComplexId = orderDAO.findAllByResidentialComplexId(savedResidentialComplex.getId());
        assertTrue(orderListByResidentialComplexId.isEmpty());

        Order savedOrder = orderDAO.save(order);

        orderListByResidentialComplexId = orderDAO.findAllByResidentialComplexId(savedResidentialComplex.getId());
        assertFalse(orderListByResidentialComplexId.isEmpty());
        assertEquals(savedOrder, orderListByResidentialComplexId.get(0));
    }
}
