package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.SpecialistExpertiseAreaDto;
import com.example.cloudComputing.entity.AddJobExpertiseRequest;
import com.example.cloudComputing.entity.AddSpecialistExpertiseRequest;
import com.example.cloudComputing.mapper.SpecialistExpertiseAreaMapper;
import com.example.cloudComputing.serviceImpl.SpecialistExpertiseAreaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<?> addspecialistExpertiseArea(@RequestBody AddSpecialistExpertiseRequest request) {
    try{
      specialistExpertiseAreaServiceImpl.add(request.getSpecialistId(), request.getExpertiseAreas());
      return ResponseEntity.ok("{\"message\": \"Expertise Areas added successfully to the Specialist\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding expertise areas");
    }
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




