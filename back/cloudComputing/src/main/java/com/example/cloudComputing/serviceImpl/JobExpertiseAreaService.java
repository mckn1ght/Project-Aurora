package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobExpertiseAreaDto;

import java.time.LocalDate;
import java.util.List;

public interface JobExpertiseAreaService {
  void add(Long jobId, List<Integer> expertiseAreas);


  List<JobExpertiseAreaDto> getByJobId(Long id);
  List<JobExpertiseAreaDto> getByExpertiseAreaId(Long id);

}
