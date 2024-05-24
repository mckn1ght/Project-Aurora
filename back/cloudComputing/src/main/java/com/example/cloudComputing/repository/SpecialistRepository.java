package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.entity.Bid;
import com.example.cloudComputing.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
  SpecialistDto save(SpecialistDto specialistDto);

  List<Specialist> findAll();

//  Specialist getById(Long id);

  @Override
  Specialist getReferenceById(Long aLong);

}
