package com.elcentr.service.impl;

import com.elcentr.dao.ProductEnclosureDAO;
import com.elcentr.model.ProductEnclosure;
import com.elcentr.service.ProductEnclosureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEnclosureServiceImpl implements ProductEnclosureService {

    private final ProductEnclosureDAO productEnclosureDAO;

    @Override
    public ProductEnclosure create(ProductEnclosure productEnclosure) {
        return null;
    }    //TODO

    @Override
    public ProductEnclosure update(ProductEnclosure productEnclosure) {
        return null;
    }    //TODO

    @Override
    public ProductEnclosure findOneById(Integer id) {
        return null;
    }    //TODO

    @Override
    public List<ProductEnclosure> findAll() {
        return null;
    }    //TODO

    @Override
    public void deleteById(Integer id) {

    }    //TODO

    @Override
    public List<ProductEnclosure> findAllByProductId(Integer productId) {
        return null;
    }    //TODO

    @Override
    public List<ProductEnclosure> findAllByEnclosureId(Integer enclosureId) {
        return null;
    }    //TODO
}
