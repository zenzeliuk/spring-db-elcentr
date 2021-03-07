package com.elcentr.dao;

import com.elcentr.model.Order;
import com.elcentr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    Order findByProduct (Product product);

}
