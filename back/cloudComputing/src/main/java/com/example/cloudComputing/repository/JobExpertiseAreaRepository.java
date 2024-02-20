package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.JobExpertiseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobExpertiseAreaRepository extends JpaRepository<JobExpertiseArea, Long> {
  JobExpertiseArea save(JobExpertiseArea jobExpertiseAreaDto);

  List<JobExpertiseArea> getByJobId(Long id);
  List<JobExpertiseArea> getByExpertiseAreaId(Long id);



}



