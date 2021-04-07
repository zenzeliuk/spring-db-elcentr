package com.elcentr.service.impl;

import com.elcentr.dao.CustomerDAO;
import com.elcentr.model.Customer;
import com.elcentr.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static ch.qos.logback.core.util.OptionHelper.isEmpty;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    @Override
    public Customer create(Customer customer) {
        if (isNull(customer.getId()) &&
                !isEmpty(customer.getName()) &&
                !findAll().contains(customer))
            return customerDAO.save(customer);

        throw new RuntimeException("Customer can not be created.");
    }

    @Override
    public Customer update(Customer customer) {
        if (nonNull(customer.getId()) &&
                !isEmpty(customer.getName()) &&
                !findAll().contains(customer)
        ) {
            return customerDAO.save(customer);
        }
        throw new RuntimeException("Customer can not be updated.");
    }

    @Override
    public Customer findOneById(Integer id) {
        return customerDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer was not found"));
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        customerDAO.deleteById(id);
    }

    @Override
    public List<Customer> findAllByFilter(String name, String notes) {
        return new ArrayList<>();
    } //TODO
}
