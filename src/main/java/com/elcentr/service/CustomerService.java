package com.elcentr.service;

import com.elcentr.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findOneById(Integer id);

    List<Customer> findAll();

    void deleteById(Integer id);

    List<Customer> findAllByFilter(String name, String notes);

}
