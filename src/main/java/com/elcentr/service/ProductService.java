package com.elcentr.service;

import com.elcentr.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product update(Product product);

    Product findOneById(Integer id);

    List<Product> findAll();

    void deleteById(Integer id);

    List<Product> findAllByFilter
            (
                    String name,
                    String code,
                    Integer nominalCurrent,
                    Integer indexProtection
            );

}
