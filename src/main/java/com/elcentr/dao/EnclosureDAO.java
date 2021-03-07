package com.elcentr.dao;

import com.elcentr.model.Enclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureDAO extends JpaRepository<Enclosure, Integer> {
}
