package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.SpecialistExpertiseAreaDto;
import com.example.cloudComputing.mapper.SpecialistExpertiseAreaMapper;
import com.example.cloudComputing.serviceImpl.SpecialistExpertiseAreaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/specialistExpertiseArea")
public class SpecialistExpertiseAreaController {
  @Autowired
  private SpecialistExpertiseAreaServiceImpl specialistExpertiseAreaServiceImpl;

  @Autowired
  SpecialistExpertiseAreaMapper specialistExpertiseAreaMapper;

  @PostMapping("/add")
  public ResponseEntity<String> addspecialistExpertiseArea(@Valid @RequestBody SpecialistExpertiseAreaDto specialistExpertiseAreaDto) {
    ResponseEntity.ok(specialistExpertiseAreaServiceImpl.add(specialistExpertiseAreaDto));
    return ResponseEntity.ok("{\"message\": \"Expertise Area added succesfully to the specialist\"}");
  }
  @GetMapping("/all")
  public List<SpecialistExpertiseAreaDto> getAll() {
    return specialistExpertiseAreaServiceImpl.getAll();
  }

  @GetMapping("/specialistId/{id}")
  public ResponseEntity<List<SpecialistExpertiseAreaDto>> getAllWithSpecialistId(@PathVariable Long specialistId) {
    return ResponseEntity.ok(specialistExpertiseAreaServiceImpl.getBySpecialistId(specialistId));
  }

  @GetMapping("/expertiseAreaId/{Id}")
  public ResponseEntity<List<SpecialistExpertiseAreaDto>> getAllWithExpAreaId(@PathVariable Long expertiseAreaId) {
    return ResponseEntity.ok(specialistExpertiseAreaServiceImpl.getByExpertiseAreaId(expertiseAreaId));
  }


}




