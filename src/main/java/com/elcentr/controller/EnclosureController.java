package com.elcentr.controller;

import com.elcentr.model.Enclosure;
import com.elcentr.service.EnclosureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("enclosure")
@AllArgsConstructor
public class EnclosureController {

    private final EnclosureService enclosureService;

    @PutMapping
    public ResponseEntity<Enclosure> create(@RequestBody Enclosure enclosure) {
        try {
            return new ResponseEntity<>(enclosureService.create(enclosure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Enclosure> update(@RequestBody Enclosure enclosure) {
        try {
            return new ResponseEntity<>(enclosureService.update(enclosure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enclosure> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(enclosureService.findOneById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Enclosure>> findAll() {
        return new ResponseEntity<>(enclosureService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-by-filters")
    public ResponseEntity<List<Enclosure>> findAllByFilter(
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer height,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer depth) {
        return new ResponseEntity<>(enclosureService.findAllByFilter(manufacturer, code, category, name, height, width, depth), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            enclosureService.deleteById(id);
        } catch (Exception e) {
            log.warn("Delete method was processed with exception for enclosure with id {}", id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
