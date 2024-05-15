package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobPictureDto;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaFoundException;
import com.example.cloudComputing.exception.job.NoJobFoundException;
import com.example.cloudComputing.mapper.JobPictureMapper;
import com.example.cloudComputing.mapper.SpecialistExpertiseAreaMapper;
import com.example.cloudComputing.repository.JobPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class JobPictureServiceImpl implements JobPictureService {

  @Autowired
  private JobPictureRepository jobPictureRepository;

  @Autowired
  private JobPictureMapper jobPictureMapper;



  public String add(JobPictureDto jobPictureDto) {
    // Convert DTO to Entity and save
    jobPictureRepository.save(jobPictureMapper.mapToJobPicture(jobPictureDto));
    return "Job picture added successfully";
  }

  public List<JobPictureDto> getAllByJobId(int jobId) {
    List<JobPictureDto> jobPictureDtos = jobPictureRepository.getAllByJobId(jobId).stream().map(jobPictureDto ->
      jobPictureMapper.mapToJobPictureDto(jobPictureDto)).collect(Collectors.toList());
    if (jobPictureDtos.isEmpty()) {
      throw new NoJobFoundException(NO_RESOURCE_FOUND);
    }
    return jobPictureDtos;
  }

}

