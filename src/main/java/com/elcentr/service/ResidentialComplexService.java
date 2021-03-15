package com.elcentr.service;

import com.elcentr.model.ResidentialComplex;

import java.util.List;

public interface ResidentialComplexService {

    ResidentialComplex create(ResidentialComplex residentialComplex);

    ResidentialComplex update(ResidentialComplex residentialComplex);

    ResidentialComplex findOneById(Integer id);

    List<ResidentialComplex> findAll();

    void deleteById(Integer id);

    List<ResidentialComplex> findAllByFilter(String name, String address);

}
