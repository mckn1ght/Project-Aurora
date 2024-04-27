package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.dto.JobExpertiseAreaDto;
import com.example.cloudComputing.entity.JobExpertiseArea;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaFoundException;
import com.example.cloudComputing.exception.job.NoJobFoundException;
import com.example.cloudComputing.mapper.JobExpertiseAreaMapper;
import com.example.cloudComputing.repository.JobExpertiseAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class JobExpertiseAreaServiceImpl implements JobExpertiseAreaService {

  @Autowired
  private JobExpertiseAreaRepository jobExpertiseAreaRepository;

  @Autowired
  private JobExpertiseAreaMapper jobExpertiseAreaMapper;
  @Autowired
  public JobExpertiseAreaServiceImpl(JobExpertiseAreaRepository jobExpertiseAreaRepository, JobExpertiseAreaMapper jobExpertiseAreaMapper) {
    this.jobExpertiseAreaRepository = jobExpertiseAreaRepository;
    this.jobExpertiseAreaMapper = jobExpertiseAreaMapper;
  }

  public void add(Long jobId, List<Integer> expertiseAreas) {
    System.out.println("Adding expertise areas to job.");
    for (Integer expertiseAreaId : expertiseAreas) {
      JobExpertiseAreaDto jobExpertiseAreaDto = new JobExpertiseAreaDto();
      jobExpertiseAreaDto.setJobId(Math.toIntExact(jobId));
      jobExpertiseAreaDto.setExpertiseAreaId(expertiseAreaId);
      jobExpertiseAreaRepository.save(jobExpertiseAreaMapper.mapToJobExpertiseArea(jobExpertiseAreaDto));
    }
  }

  public List<JobExpertiseAreaDto> getAll() {
    List<JobExpertiseAreaDto> jobExpertiseAreaDtos = jobExpertiseAreaRepository.findAll().stream().map(jobExpertiseAreaDto ->
      jobExpertiseAreaMapper.mapToJobExpertiseAreaDto(jobExpertiseAreaDto)).collect(Collectors.toList());
    if (jobExpertiseAreaDtos.isEmpty()) {
      throw new NoJobFoundException(NO_JOB_FOUND);
    }
    return jobExpertiseAreaDtos;
  }


  public List<JobExpertiseAreaDto> getByExpertiseAreaId(Long expertiseAreaid) {
    List<JobExpertiseAreaDto> jobExpertiseAreaDtos = jobExpertiseAreaRepository.getByExpertiseAreaId(expertiseAreaid).stream().map(jobExpertiseArea ->
      jobExpertiseAreaMapper.mapToJobExpertiseAreaDto(jobExpertiseArea)).collect(Collectors.toList());
    if (jobExpertiseAreaDtos.isEmpty()) {
      throw new NoExpertiseAreaFoundException(String.format(NO_EXPAREA_ID_FOUND, expertiseAreaid));
    }
    return jobExpertiseAreaDtos;
  }

  public List<JobExpertiseAreaDto> getByJobId(Long jobId) {
      List<JobExpertiseAreaDto> jobExpertiseAreaDtos =  jobExpertiseAreaRepository.getByJobId(jobId).stream().map(jobExpertiseArea ->
        jobExpertiseAreaMapper.mapToJobExpertiseAreaDto(jobExpertiseArea)).collect(Collectors.toList());
      if (jobExpertiseAreaDtos.isEmpty()) {
        throw new NoExpertiseAreaFoundException(String.format(NO_JOB_ID_FOUND, jobId));
      }
      return jobExpertiseAreaDtos;
    }

}

