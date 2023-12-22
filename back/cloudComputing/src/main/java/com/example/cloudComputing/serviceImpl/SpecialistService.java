package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.dto.UserDto;

import java.util.List;

public interface SpecialistService {
  SpecialistDto add(SpecialistDto specialistDto);

  List<SpecialistDto> getAll();

  SpecialistDto getById(Long id);

  List<SpecialistDto> getAllByCoverageArea(String coverageArea);

  List<SpecialistDto> getAllByExpertiseArea(int expertiseArea);

  List<SpecialistDto> getAllByExpertiseAreaAndCoverageArea(int expertiseArea, String coverageArea);

}
