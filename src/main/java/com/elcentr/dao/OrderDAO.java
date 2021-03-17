package com.elcentr.dao;

import com.elcentr.model.Customer;
import com.elcentr.model.Order;
import com.elcentr.model.Product;
import com.elcentr.model.ResidentialComplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    Order findByProductId(Integer productId);

    List<Order> findAllByCustomerId(Integer customerId);

    List<Order> findAllByResidentialComplexId(Integer residentialComplexId);

}
