package com.elcentr.dao;

import com.elcentr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    Order findByProductId(Integer productId);

    List<Order> findAllByCustomerId(Integer customerId);

    List<Order> findAllByResidentialComplexId(Integer residentialComplexId);

}
