package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.SpecialistExpertiseAreaDto;
import com.example.cloudComputing.entity.SpecialistExpertiseArea;
import org.springframework.stereotype.Component;

@Component
public class SpecialistExpertiseAreaMapper {
  public SpecialistExpertiseArea mapToSpecialistExpertiseArea(SpecialistExpertiseAreaDto specialistExpertiseAreaDto) {
    return SpecialistExpertiseArea.builder()
      .id(specialistExpertiseAreaDto.getId())
      .specialistId(specialistExpertiseAreaDto.getSpecialistId())
      .expertiseAreaId(specialistExpertiseAreaDto.getExpertiseAreaId())
      .build();
  }

  public SpecialistExpertiseAreaDto mapToSpecialistExpertiseAreaDto(SpecialistExpertiseArea specialistExpertiseArea) {
    return SpecialistExpertiseAreaDto.builder()
      .id(specialistExpertiseArea.getId())
      .specialistId(specialistExpertiseArea.getSpecialistId())
      .expertiseAreaId(specialistExpertiseArea.getExpertiseAreaId())
      .build();
  }
}
