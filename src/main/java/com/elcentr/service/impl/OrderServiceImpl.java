package com.elcentr.service.impl;

import com.elcentr.dao.OrderDAO;
import com.elcentr.model.Customer;
import com.elcentr.model.Order;
import com.elcentr.model.Product;
import com.elcentr.model.ResidentialComplex;
import com.elcentr.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public Order create(Order order) {
        return null;
    }    //TODO: create method and test;

    @Override
    public Order update(Order order) {
        return null;
    }    //TODO: create method and test;

    @Override
    public Order findOneById(Integer id) {
        return null;
    }    //TODO: create method and test;

    @Override
    public List<Order> findAll() {
        return null;
    }    //TODO: create method and test;

    @Override
    public void deleteById(Integer id) {

    }    //TODO: create method and test;

    @Override
    public Order findByProductId(Integer productId) {
        return null;
    }    //TODO: create method and test;

    @Override
    public List<Order> findAllByCustomerId(Integer customerId) {
        return null;
    }    //TODO: create method and test;

    @Override
    public List<Order> findAllByResidentialComplexId(Integer residentialComplexId) {
        return null;
    }    //TODO: create method and test;
}
