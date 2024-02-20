package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobExpertiseAreaDto;

import java.time.LocalDate;
import java.util.List;

public interface JobExpertiseAreaService {
  JobExpertiseAreaDto add(JobExpertiseAreaDto jobExpertiseAreaDto);


  List<JobExpertiseAreaDto> getByJobId(Long id);
  List<JobExpertiseAreaDto> getByExpertiseAreaId(Long id);

}
