package ru.gb.zettro.simpleserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.zettro.simpleserver.repository.SampleRepository;
import ru.gb.zettro.simpleserver.repository.SampleUnit;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/units")
public class MainController {

    SampleRepository sampleRepository = SampleRepository.getInstance();

    @GetMapping
    public List<SampleUnit> getUnits(@RequestParam(defaultValue = "", name = "filter") String filter) {
        if(filter.equals("")) {
            log.info("Request for all units list received.");
            return sampleRepository.findAll();
        }
        log.info("Request for filtered units list received.");
        return sampleRepository.findAll().stream()
           .filter((sampleUnit ->
                   (sampleUnit.getName().contains(filter) ||
                   sampleUnit.getDescription().contains(filter))
                   )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SampleUnit getUnitById(@PathVariable Long id) {
        log.info("Request for unit with id = " + id + " received.");
        return sampleRepository.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteUnit(@PathVariable Long id) {
        log.info("Request for deleting unit with id = " + id + " received.");

        SampleUnit removedUnit = sampleRepository.deleteById(id);
        if (removedUnit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Long> createUnit(@RequestBody SampleUnit sampleUnit) {
        log.info("Request for adding unit received: " + sampleUnit);
        return new ResponseEntity<>(sampleRepository.saveOrUpdate(sampleUnit).getId(), HttpStatus.OK);
    }

}
