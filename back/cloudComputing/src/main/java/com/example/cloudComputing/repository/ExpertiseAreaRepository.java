package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.ExpertiseAreaDto;
import com.example.cloudComputing.entity.ExpertiseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertiseAreaRepository extends JpaRepository<ExpertiseArea, Long> {
  ExpertiseAreaDto save(ExpertiseAreaDto expertiseAreaDto);

  @Override
  ExpertiseArea getReferenceById(Long aLong);

  List<ExpertiseArea> findAll();

  ExpertiseArea getByName(String name);

  List<ExpertiseArea> getAllByNameContainingIgnoreCase(String name);

  @Query("SELECT e.name FROM ExpertiseArea e JOIN e.jobs j WHERE j.id = :jobId")
  List<String> findExpertiseAreaNamesByJobId(Long jobId);

}
