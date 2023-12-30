package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.ExpertiseAreaDto;
import com.example.cloudComputing.entity.ExpertiseArea;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaNameFoundException;
import com.example.cloudComputing.mapper.ExpertiseAreaMapper;
import com.example.cloudComputing.serviceImpl.ExpertiseAreaServiceImpl;
import com.example.cloudComputing.serviceImpl.ExpertiseAreaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.cloudComputing.constants.Constants.NO_EXPAREA_NAME_FOUND;


@Validated
@RestController
@RequestMapping("/expertiseArea")
public class ExpertiseAreaController {
  @Autowired
  private ExpertiseAreaServiceImpl expertiseAreaServiceImpl;

  @Autowired
  ExpertiseAreaMapper expertiseAreaMapper;

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> addExpertiseArea(@Valid @RequestBody ExpertiseAreaDto expertiseAreaDto) {
      ResponseEntity.ok(expertiseAreaServiceImpl.add(expertiseAreaDto));
      return ResponseEntity.ok("{\"message\": \"ExpertiseArea added successful\"}");
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<ExpertiseAreaDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(expertiseAreaServiceImpl.getReferenceById(id));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/all")
  public List<ExpertiseAreaDto> getAll() {
    return expertiseAreaServiceImpl.getAll();
  }

  @GetMapping("/allByName/{name}")
  public ResponseEntity<ExpertiseAreaDto> getByName(@PathVariable String name) {
    return ResponseEntity.ok(expertiseAreaServiceImpl.getByName(name));
  }

  @GetMapping("/allByNameContaining/{name}")
  public ResponseEntity<List<ExpertiseAreaDto>> getAllByNameContainingIgnoreCase(@PathVariable String name) {
    return ResponseEntity.ok(expertiseAreaServiceImpl.getAllByNameContainingIgnoreCase(name));
  }

}
