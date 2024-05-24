package com.example.cloudComputing.mapper;


import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.entity.Specialist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialistMapper {
  public Specialist mapToSpecialist(SpecialistDto specialistDto) {
    return Specialist.builder()
      .id(specialistDto.getId())
      .rating(specialistDto.getRating())
      .build();
  }

  public SpecialistDto mapToSpecialistDto(Specialist specialist) {
    return SpecialistDto.builder()
      .id(specialist.getId())
      .rating(specialist.getRating())
      .build();
  }

  public List<SpecialistDto> mapToSpecialistDtoList(List<Specialist> specialists) {
    return specialists.stream()
      .map(this::mapToSpecialistDto)
      .collect(Collectors.toList());
  }
}
