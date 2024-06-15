package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Job;
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
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/job")
public class JobController {
  @Autowired
  private JobServiceImpl jobServiceImpl;

  @Autowired
  JobMapper jobMapper;

  @CrossOrigin(origins = "http://localhost:4200")
//  @PostMapping("/add")
//  public ResponseEntity<String> addjob(@Valid @RequestBody JobDto jobDto) {
//    ResponseEntity.ok(jobServiceImpl.add(jobDto));
//    return ResponseEntity.ok("{\"message\": \"Job posted successful\"}");
//  }
  @PostMapping("/add")
  public ResponseEntity<Object> addJob(@Valid @RequestBody JobDto jobDto) {
    Long addedJobId = jobServiceImpl.add(jobDto);
    // Return the ID of the added job
    return ResponseEntity.ok().body("{\"jobId\": " + addedJobId + "}");
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<JobDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(jobServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<JobDto> getAll() {
    return jobServiceImpl.getAll();
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/title/{title}")
  public ResponseEntity<List<JobDto>> getAllByTitleContainingIgnoreCase(@PathVariable String title) {
    return ResponseEntity.ok(jobServiceImpl.getAllByTitleContainingIgnoreCase(title));
  }
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/description/{description}")
  public ResponseEntity<List<JobDto>> getAllByDescriptionContainingIgnoreCase(@PathVariable String description) {
    return ResponseEntity.ok(jobServiceImpl.getAllByDescriptionContainingIgnoreCase(description));
  }

  @CrossOrigin(origins = "http://localhost:4200")
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

  @GetMapping("/inWorkflowStep/")
  public ResponseEntity<List<JobDto>> getAllByCurrentWorkflowStep() {
    return ResponseEntity.ok(jobServiceImpl.getAllByCurrentWorkflowStep());
  }
  @GetMapping("/allNewCreatedByUser/{userId}")
  public ResponseEntity<List<JobDto>> getAllByCurrentWorkflowStep(@PathVariable Long userId) {
    return ResponseEntity.ok(jobServiceImpl.getNewJobsCreatedByUser(userId));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/{jobId}/assign/{specialistId}")
  public ResponseEntity<String> assignJobToSpecialist(@PathVariable long jobId, @PathVariable int specialistId) {
    if (jobServiceImpl.isJobAvailableForTaking(jobId)) {
      jobServiceImpl.assignJobToSpecialist(jobId, specialistId);
      return ResponseEntity.ok("Job assigned to specialist successfully.");
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Job is already assigned.");
    }
  }

  @DeleteMapping("/deleteById/{jobId}")
  public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
    try {
      jobServiceImpl.deleteById(jobId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/createdBy/{userId}/withBids")
  public List<Object[]> getJobsAndBidsCreatedByUser(@PathVariable Long userId) {
    return jobServiceImpl.getJobsAndBidsCreatedByUser(userId);
  }

  @GetMapping("/createdBy/{userId}/status/preluat-or-in-executie")
  public List<JobDto> getJobsCreatedByUserWithStatus(@PathVariable Long userId) {
    return jobServiceImpl.getAllByCreatedByUserAndActive(userId);
  }

  @PostMapping("/{jobId}/workflow/{workflowStatus}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Object> setWorkflowStatus(@PathVariable long jobId, @PathVariable String workflowStatus) {
    try {
      jobServiceImpl.setWorkflowStatus(jobId, workflowStatus);
      return ResponseEntity.ok().body(Map.of("message", "Workflow status of job with ID " + jobId + " updated successfully"));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }
  }

  @GetMapping("/countCreatedBy/{userId}/")
  public int countJobsCreatedByUser(@PathVariable Long userId) {
    return jobServiceImpl.countJobsCreatedByUser(userId);
  }

  @GetMapping("/countJobsFinishedBySpecialist/{specialistId}/")
  public int countJobsFinishedBySpecialist(@PathVariable Long specialistId) {
    return jobServiceImpl.countJobsFinishedBySpecialist(specialistId);
  }

  @GetMapping("/countActiveJobsInWorkBySpecialist/{specialistId}/")
  public int countActiveJobsInWorkBySpecialist(@PathVariable Long specialistId) {
    return jobServiceImpl.countActiveJobsInWorkBySpecialist(specialistId);
  }
  @GetMapping("/countJobsFinishedByUser/{userId}/")
  public int countJobsFinishedByUser(@PathVariable Long userId) {
    return jobServiceImpl.countJobsFinishedByUser(userId);
  }

  @GetMapping("/countJobsCanceledByUser/{userId}/")
  public int countJobsCanceledByUser(@PathVariable Long userId) {
    return jobServiceImpl.countJobsCanceledByUser(userId);
  }

  @GetMapping("/getByExpertiseArea/{expertiseAreaId}")
  public List<Job> getJobsByExpertiseAreaId(@PathVariable int expertiseAreaId) {
    return jobServiceImpl.getJobsByExpertiseAreaId(expertiseAreaId);
  }

  @GetMapping("/{jobId}/expertiseAreas/")
  public ResponseEntity<List<String>> getExpertiseAreaNames(@PathVariable Long jobId) {
    List<String> expertiseAreaNames = jobServiceImpl.getExpertiseAreaNamesByJobId(jobId);
    if (expertiseAreaNames.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(expertiseAreaNames);
  }

  @PutMapping("/update/{jobId}")
  public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job updatedJob) {
    Job job = jobServiceImpl.updateJob(jobId, updatedJob);
    if (job == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(job);
  }

}




