package com.elcentr.dao;

import com.elcentr.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

    @Test
    void crudCustomer() {
        Customer customer = Customer.builder()
                .name("customer-test")
                .notes("customer-notes")
                .build();
        Customer savedCustomer = customerDAO.save(customer);

        assertNotNull(savedCustomer.getId());
        assertEquals(savedCustomer, customerDAO.findById(savedCustomer.getId()).get());

        List<Customer> customerList = customerDAO.findAll();

        assertTrue(customerList.contains(savedCustomer));
        customerDAO.delete(savedCustomer);
        customerList = customerDAO.findAll();
        assertFalse(customerList.contains(savedCustomer));

    }

    @Test
    void createAndDeleteCustomer() {
        Customer customer = Customer.builder()
                .name("customer-test")
                .notes("customer-notes")
                .build();
        Customer savedCustomer = customerDAO.save(customer);

        assertNotNull(savedCustomer.getId());

        customerDAO.delete(savedCustomer);

    }
}
