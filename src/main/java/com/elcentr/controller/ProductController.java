package com.elcentr.controller;

import com.elcentr.model.Product;
import com.elcentr.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-by-filters")
    public ResponseEntity<List<Product>> findAllByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String nominalCurrent,
            @RequestParam(required = false) String ip) {
        return new ResponseEntity<>(productService.findAllByFilter(name, code, nominalCurrent, ip), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
