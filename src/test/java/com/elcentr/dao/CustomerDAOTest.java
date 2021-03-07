package com.elcentr.dao;

import com.elcentr.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

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
