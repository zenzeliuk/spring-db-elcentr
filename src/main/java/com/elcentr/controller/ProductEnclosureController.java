package com.elcentr.controller;

import com.elcentr.model.ProductEnclosure;
import com.elcentr.service.ProductEnclosureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("product-enclosure")
@AllArgsConstructor
public class ProductEnclosureController {

    private final ProductEnclosureService productEnclosureService;

    @PutMapping
    public ResponseEntity<ProductEnclosure> create(@RequestBody ProductEnclosure productEnclosure) {
        try {
            return new ResponseEntity<>(productEnclosureService.create(productEnclosure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ProductEnclosure> update(@RequestBody ProductEnclosure productEnclosure) {
        try {
            return new ResponseEntity<>(productEnclosureService.update(productEnclosure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEnclosure> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productEnclosureService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductEnclosure>> findAllByProductId(@PathVariable Integer productId) {
        try {
            return new ResponseEntity<>(productEnclosureService.findAllByProductId(productId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{enclosureId}")
    public ResponseEntity<List<ProductEnclosure>> findAllByEnclosureId(@PathVariable Integer enclosureId) {
        try {
            return new ResponseEntity<>(productEnclosureService.findAllByEnclosureId(enclosureId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductEnclosure>> findAll() {
        return new ResponseEntity<>(productEnclosureService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            productEnclosureService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for productEnclosure with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
