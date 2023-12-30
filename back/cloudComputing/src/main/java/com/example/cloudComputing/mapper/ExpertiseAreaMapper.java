package com.example.cloudComputing.mapper;


import com.example.cloudComputing.dto.ExpertiseAreaDto;
import com.example.cloudComputing.entity.ExpertiseArea;
import org.springframework.stereotype.Component;

@Component
public class ExpertiseAreaMapper {
  public ExpertiseArea mapToExpertiseArea(ExpertiseAreaDto expertiseAreaDto) {
    return ExpertiseArea.builder()
      .id(expertiseAreaDto.getId())
      .name(expertiseAreaDto.getName())
      .iconName(expertiseAreaDto.getIconName())
      .build();
  }

  public ExpertiseAreaDto mapToExpertiseAreaDto(ExpertiseArea expertiseArea) {
    return ExpertiseAreaDto.builder()
      .id(expertiseArea.getId())
      .name(expertiseArea.getName())
      .iconName(expertiseArea.getIconName())
      .build();
  }
}
