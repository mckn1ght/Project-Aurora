package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.mapper.ResourceMapper;
import com.example.cloudComputing.serviceImpl.ResourceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/resource")
public class ResourceController {
  @Autowired
  private ResourceServiceImpl resourceServiceImpl;

  @Autowired
  ResourceMapper resourceMapper;
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> add(@Valid @RequestBody ResourceDto resourceDto) {
      ResponseEntity.ok(resourceServiceImpl.add(resourceDto));
      return ResponseEntity.ok("{\"message\": \"Resource added succesfully/\"}");

  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<ResourceDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(resourceServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<ResourceDto> getAll() {
    return resourceServiceImpl.getAll();
  }

  @GetMapping("/getByUserId/{userId}")
  public List<ResourceDto> findAllByUserId(@PathVariable int userId){
    return resourceServiceImpl.findAllByUserId(userId);
  }

  @GetMapping("/getByUserId/{userId}/type/{type}")
  public ResourceDto findByUserIdAndAndType(@PathVariable int userId, @PathVariable String type){
    return resourceServiceImpl.findByUserIdAndAndType(userId, type);
  }
}

