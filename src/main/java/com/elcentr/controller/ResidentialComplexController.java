package com.elcentr.controller;

import com.elcentr.model.ResidentialComplex;
import com.elcentr.service.ResidentialComplexService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("residential-complex")
@AllArgsConstructor
public class ResidentialComplexController {

    private final ResidentialComplexService residentialComplexService;

    @PutMapping
    public ResponseEntity<ResidentialComplex> create(@RequestBody ResidentialComplex residentialComplex) {
        try {
            return new ResponseEntity<>(residentialComplexService.create(residentialComplex), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResidentialComplex> update(@RequestBody ResidentialComplex residentialComplex) {
        try {
            return new ResponseEntity<>(residentialComplexService.update(residentialComplex), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentialComplex> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(residentialComplexService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ResidentialComplex>> findAll() {
        return new ResponseEntity<>(residentialComplexService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-by-filters")
    public ResponseEntity<List<ResidentialComplex>> findAllByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        return new ResponseEntity<>(residentialComplexService.findAllByFilter(name, address), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            residentialComplexService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for residential complex with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
