package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.JobPicture;
import com.example.cloudComputing.entity.SpecialistExpertiseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPictureRepository extends JpaRepository<JobPicture, Long> {
  JobPicture save(JobPicture jobPictureDto);

  List<JobPicture> getAllByJobId(int id);

  JobPicture getByUrl(String url);

}



