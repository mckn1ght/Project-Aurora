package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Job;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobMapper {
  public Job mapToJob(JobDto jobDto) {
    return Job.builder()
      .id(jobDto.getId())
      .title(jobDto.getTitle())
      .description(jobDto.getDescription())
      .location(jobDto.getLocation())
      .createdByUser(jobDto.getCreatedByUser())
      .assignedToSpecialist(jobDto.getAssignedToSpecialist())
      .creationDate(jobDto.getCreationDate())
      .dueDate(jobDto.getDueDate())
      .lastUpdated(jobDto.getLastUpdated())
      .currentWorkflowStep(jobDto.getCurrentWorkflowStep())
      .build();
  }

  public JobDto mapToJobDto(Job job) {
    return JobDto.builder()
      .id(job.getId())
      .title(job.getTitle())
      .description(job.getDescription())
      .location(job.getLocation())
      .createdByUser(job.getCreatedByUser())
      .assignedToSpecialist(job.getAssignedToSpecialist())
      .creationDate(job.getCreationDate())
      .dueDate(job.getDueDate())
      .lastUpdated(job.getLastUpdated())
      .currentWorkflowStep(job.getCurrentWorkflowStep())
      .build();
  }

}
