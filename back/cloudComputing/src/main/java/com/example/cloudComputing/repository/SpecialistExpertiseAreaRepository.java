package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.SpecialistExpertiseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistExpertiseAreaRepository extends JpaRepository<SpecialistExpertiseArea, Long> {
  SpecialistExpertiseArea save(SpecialistExpertiseArea specialistExpertiseAreaDto);

  List<SpecialistExpertiseArea> getBySpecialistId(Long id);
  List<SpecialistExpertiseArea> getByExpertiseAreaId(Long id);



}



