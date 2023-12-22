package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.exception.job.*;
import com.example.cloudComputing.mapper.JobMapper;
import com.example.cloudComputing.serviceImpl.JobServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;


@Validated
@RestController
@RequestMapping("/job")
public class JobController {
  @Autowired
  private JobServiceImpl jobServiceImpl;

  @Autowired
  JobMapper jobMapper;

//  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> addjob(@Valid @RequestBody JobDto jobDto) {
    ResponseEntity.ok(jobServiceImpl.add(jobDto));
    return ResponseEntity.ok("{\"message\": \"Job posted successful\"}");
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<JobDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(jobServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<JobDto> getAll() {
    return jobServiceImpl.getAll();
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<List<JobDto>> getAllByTitleContainingIgnoreCase(@PathVariable String title) {
    return ResponseEntity.ok(jobServiceImpl.getAllByTitleContainingIgnoreCase(title));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<JobDto>> getAllByDescriptionContainingIgnoreCase(@PathVariable String description) {
    return ResponseEntity.ok(jobServiceImpl.getAllByDescriptionContainingIgnoreCase(description));
  }

  @GetMapping("/location/{location}")
  public ResponseEntity<List<JobDto>> getAllByLocationContainingIgnoreCase(@PathVariable String location) {
    return ResponseEntity.ok(jobServiceImpl.getAllByLocationContainingIgnoreCase(location));
  }

  @GetMapping("/createdBy/{id}")
  public ResponseEntity<List<JobDto>> getAllByCreatedByUser(@PathVariable int id) {
    return ResponseEntity.ok(jobServiceImpl.getAllByCreatedByUser(id));
  }

  @GetMapping("/assignedTo/{id}")
  public ResponseEntity<List<JobDto>> getAllByAssignedToSpecialist(@PathVariable int id) {
    return ResponseEntity.ok(jobServiceImpl.getAllByAssignedToSpecialist(id));
  }

  @GetMapping("/created/{date}")
  public ResponseEntity<List<JobDto>> getAllByCreationDate(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByCreationDate(date));
  }

  @GetMapping("/createdBefore/{date}")
  public ResponseEntity<List<JobDto>> getAllByCreationDateBefore(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByCreationDateBefore(date));
  }

  @GetMapping("/createdAfter/{date}")
  public ResponseEntity<List<JobDto>> getAllByCreationDateAfter(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByCreationDateAfter(date));
  }

  @GetMapping("/due/{date}")
  public ResponseEntity<List<JobDto>> getAllByDueDate(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByDueDate(date));
  }

  @GetMapping("/dueAfter/{date}")
  public ResponseEntity<List<JobDto>> getAllByDueDateAfter(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByDueDateAfter(date));
  }

  @GetMapping("/dueBefore/{date}")
  public ResponseEntity<List<JobDto>> getAllByDueDateBefore(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByDueDateBefore(date));
  }

  @GetMapping("/lastUpdated/{date}")
  public ResponseEntity<List<JobDto>> getAllByLastUpdated(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByLastUpdated(date));
  }

  @GetMapping("/lastUpdatedBefore/{date}")
  public ResponseEntity<List<JobDto>> getAllByLastUpdatedBefore(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByLastUpdatedBefore(date));
  }

  @GetMapping("/lastUpdatedAfter/{date}")
  public ResponseEntity<List<JobDto>> getAllByLastUpdatedAfter(@PathVariable LocalDate date) {
    return ResponseEntity.ok(jobServiceImpl.getAllByLastUpdatedAfter(date));
  }

  @GetMapping("/inWorkflowStep/{step}")
  public ResponseEntity<List<JobDto>> getAllByCurrentWorkflowStep(@PathVariable String step) {
    return ResponseEntity.ok(jobServiceImpl.getAllByCurrentWorkflowStep(step));
  }
}




