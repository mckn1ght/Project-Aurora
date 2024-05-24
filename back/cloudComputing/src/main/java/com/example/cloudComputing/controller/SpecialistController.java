package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.mapper.SpecialistMapper;
import com.example.cloudComputing.serviceImpl.SpecialistExpertiseAreaServiceImpl;
import com.example.cloudComputing.serviceImpl.SpecialistServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Validated
@RestController
@RequestMapping("/specialist")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecialistController {
  @Autowired
  private SpecialistServiceImpl specialistServiceImpl;

  @Autowired
  SpecialistMapper specialistMapper;

  @Autowired
  private SpecialistExpertiseAreaServiceImpl specialistExpertiseAreaServiceImpl;


  @PostMapping("/add")
  public ResponseEntity<Map<String, Object>> addSpecialist(@RequestParam Long userId, @RequestBody List<Integer> expertiseAreaIds) {
    try {
      // Logging the received data
      System.out.println("Received userId: " + userId);
      System.out.println("Received expertiseAreaIds: " + expertiseAreaIds);

      Specialist newSpecialist = specialistServiceImpl.add(userId);
      specialistExpertiseAreaServiceImpl.add(newSpecialist.getId().intValue(), expertiseAreaIds);
      Map<String, Object> response = new HashMap<>();
      response.put("message", "Specialist added successfully");
      response.put("specialistId", newSpecialist.getId());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      e.printStackTrace();  // Print stack trace for better debugging
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Error adding specialist"));
    }
  }


  @RequestMapping("/getById/{id}")
  public ResponseEntity<SpecialistDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(specialistServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<SpecialistDto> getAll() {
    return specialistServiceImpl.getAll();
  }

}



