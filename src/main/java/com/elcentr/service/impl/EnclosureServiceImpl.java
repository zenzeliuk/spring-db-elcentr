package com.elcentr.service.impl;

import com.elcentr.dao.EnclosureDAO;
import com.elcentr.model.Enclosure;
import com.elcentr.service.EnclosureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class EnclosureServiceImpl implements EnclosureService {

    private final EnclosureDAO enclosureDAO;

    @Override
    public Enclosure create(Enclosure enclosure) {
        if (isNull(enclosure.getId()) &&
                nonNull(enclosure.getName()) &&
                !findAll().contains(enclosure))
            return enclosureDAO.save(enclosure);

        throw new RuntimeException("Enclosure can not be created.");
    }

    @Override
    public Enclosure update(Enclosure enclosure) {
        if (nonNull(enclosure.getId()) &&
                nonNull(enclosure.getName()) &&
                !findAll().contains(enclosure)
        ) {
            return enclosureDAO.save(enclosure);
        }
        throw new RuntimeException("Enclosure can not be updated.");
    }

    @Override
    public Enclosure findOneById(Integer id) {
        return enclosureDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure was not found"));
    }

    @Override
    public List<Enclosure> findAll() {
        return enclosureDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        enclosureDAO.deleteById(id);
    }

    @Override
    public List<Enclosure> findAllByFilter(String manufacturer, String code, String category, String name, Integer height, Integer width, Integer depth) {
        return new ArrayList<>();
    } //TODO: create method and test;
}
