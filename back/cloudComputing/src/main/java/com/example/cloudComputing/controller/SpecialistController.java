package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.mapper.SpecialistMapper;
import com.example.cloudComputing.serviceImpl.SpecialistServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/specialist")
public class SpecialistController {
  @Autowired
  private SpecialistServiceImpl specialistServiceImpl;

  @Autowired
  SpecialistMapper specialistMapper;

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> addSpecialist(@Valid @RequestBody SpecialistDto specialistDto) {
      ResponseEntity.ok(specialistServiceImpl.add(specialistDto));
      return ResponseEntity.ok("{\"message\": \"Specialist added successful\"}");
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<SpecialistDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(specialistServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<SpecialistDto> getAll() {
    return specialistServiceImpl.getAll();
  }

  @GetMapping("/expertiseArea/{expertiseArea}")
  public ResponseEntity<List<SpecialistDto>> getAllByExpertiseArea(@PathVariable int expertiseArea) {
    return ResponseEntity.ok(specialistServiceImpl.getAllByExpertiseArea(expertiseArea));
  }

  @GetMapping("/coverageArea/{coverageArea}")
  public ResponseEntity<List<SpecialistDto>> getAllByCoverageArea(@PathVariable String coverageArea) {
    return ResponseEntity.ok(specialistServiceImpl.getAllByCoverageArea(coverageArea));
  }

  @GetMapping("/coverageArea/{coverageArea}/{expertiseArea}")
  public ResponseEntity<List<SpecialistDto>> getAllByExpertiseAreaAndCoverageArea(@PathVariable int expertiseArea,
                                                                                  @PathVariable String coverageArea) {
    return ResponseEntity.ok(specialistServiceImpl.getAllByExpertiseAreaAndCoverageArea(expertiseArea, coverageArea ));
  }

}



