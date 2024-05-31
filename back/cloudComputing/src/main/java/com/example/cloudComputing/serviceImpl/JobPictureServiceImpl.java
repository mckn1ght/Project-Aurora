package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobPictureDto;
import com.example.cloudComputing.entity.JobPicture;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaFoundException;
import com.example.cloudComputing.exception.job.NoJobFoundException;
import com.example.cloudComputing.mapper.JobPictureMapper;
import com.example.cloudComputing.mapper.SpecialistExpertiseAreaMapper;
import com.example.cloudComputing.repository.JobPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
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

  public boolean existsByUrlAndJobId(String url, int jobId) {
    // Implement this method to check if a picture URL already exists for the given jobId
    return jobPictureRepository.existsByUrlAndJobId(url, jobId);
  }

  public boolean deleteJobPictureByUrl(String url) {
    System.out.println("Looking for job picture with URL: " + url);

    // Normalize the URL (remove leading slash if it exists)
    String normalizedUrl = url.startsWith("/") ? url.substring(1) : url;

    // Print all stored URLs for debugging
    List<JobPicture> allJobPictures = jobPictureRepository.findAll();
    for (JobPicture jp : allJobPictures) {
      System.out.println("Stored URL: " + jp.getUrl());
    }

    JobPicture jobPicture = jobPictureRepository.findByUrl(normalizedUrl);
    if (jobPicture != null) {
      System.out.println("Found job picture with URL: " + jobPicture.getUrl());
      // Delete the file from the file system
      File file = new File(System.getProperty("user.dir") + "/" + jobPicture.getUrl());
      if (file.exists()) {
        file.delete();
      }
      jobPictureRepository.delete(jobPicture);
      return true;
    }
    System.out.println("No job picture found with URL: " + normalizedUrl);
    return false;
  }
}


