package com.elcentr.service.impl;

import com.elcentr.dao.ProductDAO;
import com.elcentr.model.Product;
import com.elcentr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public Product create(Product product) {
        if (isNull(product.getId()) &&
                isNull(productDAO.findByCode(product.getCode())) &&
                nonNull(product.getTimeRegistration()) &&
                nonNull(product.getAmount()) &&
                StringUtils.isNotBlank(product.getCode()) &&
                StringUtils.isNotBlank(product.getName())) {
            return productDAO.save(product);
        }
        throw new RuntimeException("Product can not be created.");
    }

    @Override
    public Product update(Product product) {
        Product savedProduct = findOneById(product.getId());
        if (!product.getCode().equals(savedProduct.getCode()) &&
                productDAO.findByCode(product.getCode()) != null) {
            throw new RuntimeException("Product code can not be updated.");
        }
        if (nonNull(savedProduct.getId()) &&
                nonNull(product.getTimeRegistration()) &&
                nonNull(product.getAmount()) &&
                StringUtils.isNotBlank(product.getCode()) &&
                StringUtils.isNotBlank(product.getName())) {
            return productDAO.save(product);
        }
        throw new RuntimeException("Product can not be updated.");

    }

    @Override
    public Product findOneById(Integer id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Product was not found"));
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        productDAO.deleteById(id);
    }

    @Override
    public List<Product> findAllByFilter(String name, String code, String nominalCurrent, String indexProtectionProduct) {

        return new ArrayList<>();
    }
}
