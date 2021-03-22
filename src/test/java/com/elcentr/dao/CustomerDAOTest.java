package com.elcentr.dao;

import com.elcentr.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

    private Customer customer;

    @BeforeEach
    void init() {
        customer = Customer.builder()
                .name("test-name")
                .notes("test-notes")
                .build();
    }
    @AfterEach
    void deleteAll(){
        customerDAO.deleteAll();
    }

    @Test
    void CRUDCustomer() {
        Customer savedCustomer = customerDAO.save(customer);
        assertNotNull(savedCustomer.getId());
        assertEquals(savedCustomer, customerDAO.findById(savedCustomer.getId()).get());
        List<Customer> customerList = customerDAO.findAll();
        assertTrue(customerList.contains(savedCustomer));
        customerDAO.delete(savedCustomer);
        customerList = customerDAO.findAll();
        assertFalse(customerList.contains(savedCustomer));
    }
}
