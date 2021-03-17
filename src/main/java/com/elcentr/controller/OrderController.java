package com.elcentr.controller;

import com.elcentr.model.Order;
import com.elcentr.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        try {
            return new ResponseEntity<>(orderService.create(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        try {
            return new ResponseEntity<>(orderService.update(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(orderService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Order> findOneByProduct(@PathVariable Integer productId) {
        try {
            return new ResponseEntity<>(orderService.findByProductId(productId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Order>> findAllByCustomerId(@PathVariable Integer customerId) {
        try {
            return new ResponseEntity<>(orderService.findAllByCustomerId(customerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{residentialComplexId}")
    public ResponseEntity<List<Order>> findAllByResidentialComplexId(@PathVariable Integer residentialComplexId) {
        try {
            return new ResponseEntity<>(orderService.findAllByResidentialComplexId(residentialComplexId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            orderService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for order with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
