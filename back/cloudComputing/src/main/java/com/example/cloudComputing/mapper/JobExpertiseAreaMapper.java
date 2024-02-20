package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.JobExpertiseAreaDto;
import com.example.cloudComputing.entity.JobExpertiseArea;
import org.springframework.stereotype.Component;

@Component
public class JobExpertiseAreaMapper {
  public JobExpertiseArea mapToJobExpertiseArea(JobExpertiseAreaDto jobExpertiseAreaDto) {
    return JobExpertiseArea.builder()
      .id(jobExpertiseAreaDto.getId())
      .jobId(jobExpertiseAreaDto.getJobId())
      .expertiseAreaId(jobExpertiseAreaDto.getExpertiseAreaId())
      .build();
  }

  public JobExpertiseAreaDto mapToJobExpertiseAreaDto(JobExpertiseArea jobExpertiseArea) {
    return JobExpertiseAreaDto.builder()
      .id(jobExpertiseArea.getId())
      .jobId(jobExpertiseArea.getJobId())
      .expertiseAreaId(jobExpertiseArea.getExpertiseAreaId())
      .build();
  }
}
