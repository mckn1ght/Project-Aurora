package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.JobExpertiseAreaDto;
import com.example.cloudComputing.entity.AddJobExpertiseRequest;
import com.example.cloudComputing.mapper.JobExpertiseAreaMapper;
import com.example.cloudComputing.serviceImpl.JobExpertiseAreaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/jobExpertiseArea")
public class JobExpertiseAreaController {
  @Autowired
  private JobExpertiseAreaServiceImpl jobExpertiseAreaServiceImpl;

  @Autowired
  JobExpertiseAreaMapper jobExpertiseAreaMapper;

//  @PostMapping("/add")
//  public ResponseEntity<String> addjobExpertiseArea(@Valid @RequestBody JobExpertiseAreaDto jobExpertiseAreaDto) {
//    ResponseEntity.ok(jobExpertiseAreaServiceImpl.add(jobExpertiseAreaDto));
//    return ResponseEntity.ok("{\"message\": \"Expertise Area added succesfully to the jobExpertiseArea\"}");
//  }

//  @PostMapping("/add")
//  public ResponseEntity<String> addJobExpertiseAreas(@RequestParam Long jobId, @RequestBody List<Integer> expertiseAreas) {
//    try {
//      System.out.println("Job Expertise area controller");
//      jobExpertiseAreaServiceImpl.add(jobId, expertiseAreas);
//      return ResponseEntity.ok("{\"message\": \"Expertise Areas added successfully to the job\"}");
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//  }

//  @PostMapping("/add")
//  public ResponseEntity<String> addJobExpertiseAreas(@RequestBody AddJobExpertiseRequest request) {
//    try {
//      System.out.println("Job Expertise area controller");
//      jobExpertiseAreaServiceImpl.add(request.getJobId(), request.getExpertiseAreas());
//      return ResponseEntity.ok();
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//  }
  @PostMapping("/add")
  public ResponseEntity<?> addJobExpertiseAreas(@RequestBody AddJobExpertiseRequest request) {
    try {
      jobExpertiseAreaServiceImpl.add(request.getJobId(), request.getExpertiseAreas());
      return ResponseEntity.ok("{\"message\": \"Expertise Areas added successfully to the job\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding expertise areas");
    }
  }





  @GetMapping("/jobId/{id}")
  public ResponseEntity<List<JobExpertiseAreaDto>> getAllWithJobId(@PathVariable Long jobId) {
    return ResponseEntity.ok(jobExpertiseAreaServiceImpl.getByJobId(jobId));
  }

  @GetMapping("/expertiseAreaId/{Id}")
  public ResponseEntity<List<JobExpertiseAreaDto>> getAllWithExpAreaId(@PathVariable Long expertiseAreaId) {
    return ResponseEntity.ok(jobExpertiseAreaServiceImpl.getByExpertiseAreaId(expertiseAreaId));
  }


}




