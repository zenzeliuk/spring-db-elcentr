package com.elcentr.service;

import com.elcentr.model.Enclosure;
import com.elcentr.model.Product;
import com.elcentr.model.ProductEnclosure;

import java.util.List;

public interface ProductEnclosureService {

    ProductEnclosure create(ProductEnclosure productEnclosure);

    ProductEnclosure update(ProductEnclosure productEnclosure);

    ProductEnclosure findOneById(Integer id);

    List<ProductEnclosure> findAll();

    void deleteById(Integer id);

    List<ProductEnclosure> findAllByProductId(Integer productId);

    List<ProductEnclosure> findAllByEnclosureId(Integer enclosureId);

}
