package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.Review;
import com.example.cloudComputing.exception.job.*;
import com.example.cloudComputing.exception.review.NoReviewIdFoundException;
import com.example.cloudComputing.mapper.JobMapper;
import com.example.cloudComputing.repository.ExpertiseAreaRepository;
import com.example.cloudComputing.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class JobServiceImpl implements JobService {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private JobMapper jobMapper;

  @Autowired
  private ExpertiseAreaRepository expertiseAreaRepository;

//  public JobDto add(JobDto jobDto) {
//    return jobMapper.mapToJobDto(jobRepository.save(jobMapper.mapToJob(jobDto)));
//  }

  public Long add(JobDto jobDto) {
    Job job = jobRepository.save(jobMapper.mapToJob(jobDto));
    return job.getId();
  }


  public List<JobDto> getAll() {
    List<JobDto> jobDtos = jobRepository.findAll().stream().map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDtos.isEmpty()) {
      throw new NoJobFoundException(NO_JOB_FOUND);
    }
    return jobDtos;
  }

  public JobDto getById(Long id) {
    try {
      Job job = jobRepository.getReferenceById(id);
      return jobMapper.mapToJobDto(job);
    } catch (Exception e) {
      throw new NoJobFoundException(String.format(NO_JOB_ID_FOUND, id));
    }
  }

  public List<JobDto> getAllByTitleContainingIgnoreCase(String title) {
    List<JobDto> jobDto = jobRepository.getAllByTitleContainingIgnoreCase(title).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobWithTitleContainingException(String.format(NO_JOB_WITH_TITLE_CONTAINING, title));
    }
    return jobDto;
  }

  public List<JobDto> getAllByDescriptionContainingIgnoreCase(String desc) {
    List<JobDto> jobDto = jobRepository.getAllByDescriptionContainingIgnoreCase(desc).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobWithDescriptionContainingException(String.format(NO_JOB_WITH_DESCRIPTION_CONTAINING, desc));
    }
    return jobDto;
  }

  public List<JobDto> getAllByLocationContainingIgnoreCase(String location) {
    List<JobDto> jobDto = jobRepository.getAllByLocationContainingIgnoreCase(location).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobWithLocationContainingException(String.format(NO_JOB_WITH_LOCATION_CONTAINING, location));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCreatedByUser(int id) {
    List<JobDto> jobDto = jobRepository.getAllByCreatedByUser(id).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobCreatedByUserException(String.format(NO_JOBS_CREATED_BY_USER, id));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCreatedByUserAndActive(Long id) {
    List<JobDto> jobDto = jobRepository.findJobsCreatedByUserWithStatus(id).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobCreatedByUserException(String.format(NO_JOBS_CREATED_BY_USER, id));
    }
    return jobDto;
  }

  public List<JobDto> getAllByAssignedToSpecialist(int id) {
    List<JobDto> jobDto = jobRepository.getAllByAssignedToSpecialist(id).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsAssignedToSpecialistException(String.format(NO_JOBS_ASSIGNED_TO_SPECIALIST, id));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCreationDate(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByCreationDate(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsCreatedOnDateException(String.format(NO_JOBS_CREATED_ON_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCreationDateBefore(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByCreationDateBefore(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsCreatedBeforeDateException(String.format(NO_JOBS_CREATED_BEFORE_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCreationDateAfter(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByCreationDateAfter(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsCreatedAfterDateException(String.format(NO_JOBS_CREATED_AFTER_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByDueDate(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByDueDate(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsWithDueDateException(String.format(NO_JOBS_WITH_DUE_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByDueDateAfter(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByDueDateAfter(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsAfterDueDateException(String.format(NO_JOBS_AFTER_DUE_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByDueDateBefore(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByDueDateBefore(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsBeforeDueDateException(String.format(NO_JOBS_BEFORE_DUE_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByLastUpdated(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByLastUpdated(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsLastUpdatedOnDateException(String.format(NO_JOBS_LAST_UPDATED_ON_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByLastUpdatedBefore(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByLastUpdatedBefore(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsLastUpdatedBeforeDateException(String.format(NO_JOBS_LAST_UPDATED_BEFORE_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByLastUpdatedAfter(LocalDate date) {
    List<JobDto> jobDto = jobRepository.getAllByLastUpdatedAfter(date).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsLastUpdatedAfterDateException(String.format(NO_JOBS_LAST_UPDATED_AFTER_DATE, date));
    }
    return jobDto;
  }

  public List<JobDto> getAllByCurrentWorkflowStep(String step) {
    List<JobDto> jobDto = jobRepository.getAllByCurrentWorkflowStep(step).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsOnWorkflowStepException(String.format(NO_JOBS_ON_WORKFLOW_STEP, step));
    }
    return jobDto;
  }

  @Transactional
  public void assignJobToSpecialist(long jobId, int specialistId) {
    Job job = jobRepository.findById(jobId).orElse(null);
    if (job != null) {
      if (isJobAvailableForTaking(jobId)) {
        job.setAssignedToSpecialist(specialistId);
        job.setCurrentWorkflowStep("Preluat");
        jobRepository.save(job);
      } else {
        throw new IllegalStateException("Job is already assigned to a specialist.");
      }
    } else {
      throw new NoJobFoundException(String.format(NO_JOB_ID_FOUND, jobId));
    }
  }

  public boolean isJobAvailableForTaking(long jobId) {
    Job job = jobRepository.getById(jobId);
    return job.getAssignedToSpecialist() == 1;
  }

  public void deleteById(Long jobId) {
    jobRepository.deleteById(jobId);
  }

  public List<Object[]> getJobsAndBidsCreatedByUser(Long userId) {
    return jobRepository.findJobsAndBidsCreatedByUser(userId);
  }

  public List<JobDto> getNewJobsCreatedByUser(Long userId) {
    List<JobDto> jobDto = jobRepository.findNewJobsCreatedByUser(userId).stream()
      .map(job -> jobMapper.mapToJobDto(job)).collect(Collectors.toList());
    if (jobDto.isEmpty()) {
      throw new NoJobsOnWorkflowStepException(String.format(NO_JOBS_CREATED_BY_USER, userId));
    }
    return jobDto;
  }

  public int countJobsCreatedByUser(Long userId) {
    int totalJobs = jobRepository.countJobsCreatedByUser(userId);
    return totalJobs;
  }

  public void setWorkflowStatus(long jobId, String workflowStatus) {
    Job job = jobRepository.findById(jobId).orElse(null);
    if (job != null) {
      job.setCurrentWorkflowStep(workflowStatus);
      jobRepository.save(job);
    } else {
      throw new RuntimeException(NO_JOB_ID_FOUND + jobId);
    }
  }

  public List<Job> getJobsByExpertiseAreaId(int expertiseAreaId) {
    return jobRepository.findByExpertiseAreasId(expertiseAreaId);
  }

  public List<String> getExpertiseAreaNamesByJobId(Long jobId) {
    return expertiseAreaRepository.findExpertiseAreaNamesByJobId(jobId);
  }
}

