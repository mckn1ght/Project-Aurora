package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.JobPicture;
import com.example.cloudComputing.entity.SpecialistExpertiseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPictureRepository extends JpaRepository<JobPicture, Long> {
  JobPicture save(JobPicture jobPictureDto);

  List<JobPicture> getAllByJobId(int id);

  JobPicture findByUrl(String url);

  boolean existsByUrlAndJobId(String url, int jobId);

  Optional<JobPicture> findById(int id);

  void deleteById(int id);

}



