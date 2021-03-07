package com.elcentr.dao;

import com.elcentr.model.ResidentialComplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentialComplexDAO extends JpaRepository<ResidentialComplex, Integer> {
}
