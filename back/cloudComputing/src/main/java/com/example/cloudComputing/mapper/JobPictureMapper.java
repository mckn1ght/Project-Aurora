package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.JobPictureDto;
import com.example.cloudComputing.entity.JobPicture;
import org.springframework.stereotype.Component;

@Component
public class JobPictureMapper {
  public JobPicture mapToJobPicture(JobPictureDto jobPictureDto) {
    return JobPicture.builder()
      .id(jobPictureDto.getId())
      .jobId(jobPictureDto.getJobId())
      .url(jobPictureDto.getUrl())
      .build();
  }

  public JobPictureDto mapToJobPictureDto(JobPicture jobPicture) {
    return JobPictureDto.builder()
      .id(jobPicture.getId())
      .jobId(jobPicture.getJobId())
      .url(jobPicture.getUrl())
      .build();
  }
}
