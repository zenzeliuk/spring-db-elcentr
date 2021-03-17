package com.elcentr.service.impl;

import com.elcentr.dao.ResidentialComplexDAO;
import com.elcentr.model.ResidentialComplex;
import com.elcentr.service.ResidentialComplexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ResidentialComplexServiceImpl implements ResidentialComplexService {

    private final ResidentialComplexDAO residentialComplexDAO;

    @Override
    public ResidentialComplex create(ResidentialComplex residentialComplex) {
        if (isNull(residentialComplex.getId()) &&
                nonNull(residentialComplex.getName()) &&
                !findAll().contains(residentialComplex))
            return residentialComplexDAO.save(residentialComplex);

        throw new RuntimeException("ResidentialComplex can not be created.");
    }

    @Override
    public ResidentialComplex update(ResidentialComplex residentialComplex) {
        if (nonNull(residentialComplex.getId()) &&
                nonNull(residentialComplex.getName()) &&
                !findAll().contains(residentialComplex)
        ) {
            return residentialComplexDAO.save(residentialComplex);
        }
        throw new RuntimeException("ResidentialComplex can not be updated.");
    }

    @Override
    public ResidentialComplex findOneById(Integer id) {
        return residentialComplexDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("ResidentialComplex was not found"));
    }

    @Override
    public List<ResidentialComplex> findAll() {
        return residentialComplexDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        residentialComplexDAO.deleteById(id);
    }

    @Override
    public List<ResidentialComplex> findAllByFilter(String name, String address) {
        return new ArrayList<>();
    } //TODO: create method and test;
}
