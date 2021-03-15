package com.elcentr.service;

import com.elcentr.model.Enclosure;

import java.util.List;

public interface EnclosureService {

    Enclosure create(Enclosure enclosure);

    Enclosure update(Enclosure enclosure);

    Enclosure findOneById(Integer id);

    List<Enclosure> findAll();

    void deleteById(Integer id);

    List<Enclosure> findAllByFilter
            (
                    String manufacturer,
                    String code,
                    String category,
                    String name,
                    Integer height,
                    Integer width,
                    Integer depth
            );


}
