package com.elcentr.service.impl;

import com.elcentr.dao.CustomerDAO;
import com.elcentr.dao.ProductDAO;
import com.elcentr.model.Customer;
import com.elcentr.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    @Override
    public Customer create(Customer customer) {
        if (nonNull(customer.getId())) {
            throw new RuntimeException("Creation is failed!");
        }
        if (!findAll().contains(customer)) {
            return Optional.of(customerDAO.save(customer));
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public Customer findOneById(Integer id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Customer> findAllByFilter(String name, String notes) {
        return null;
    }
}
