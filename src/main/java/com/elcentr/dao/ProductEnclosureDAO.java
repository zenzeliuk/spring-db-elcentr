package com.elcentr.dao;

import com.elcentr.model.ProductEnclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEnclosureDAO extends JpaRepository<ProductEnclosure, Integer> {

    List<ProductEnclosure> findAllByProductId(Integer productId);

    List<ProductEnclosure> findAllByEnclosureId(Integer enclosureId);

}
