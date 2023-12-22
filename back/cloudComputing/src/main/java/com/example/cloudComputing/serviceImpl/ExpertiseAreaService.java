package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.ExpertiseAreaDto;
import com.example.cloudComputing.entity.ExpertiseArea;

import java.util.List;

public interface ExpertiseAreaService {
  ExpertiseAreaDto add(ExpertiseAreaDto expertiseAreaDto);

  List<ExpertiseAreaDto> getAll();

  ExpertiseAreaDto getReferenceById(Long id);

  ExpertiseAreaDto getByName(String name);

  List<ExpertiseAreaDto> getAllByNameContainingIgnoreCase(String name);

}
