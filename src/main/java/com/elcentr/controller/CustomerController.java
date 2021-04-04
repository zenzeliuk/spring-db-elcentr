package com.elcentr.controller;

import com.elcentr.model.Customer;
import com.elcentr.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.create(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(customerService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-by-filters")
    public ResponseEntity<List<Customer>> findAllByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String notes) {
        return new ResponseEntity<>(customerService.findAllByFilter(name, notes), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            customerService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for customer with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
