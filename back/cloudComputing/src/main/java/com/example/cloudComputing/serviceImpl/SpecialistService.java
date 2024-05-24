package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.Specialist;

import java.util.List;

public interface SpecialistService {
  Specialist add(Long userId);

  List<SpecialistDto> getAll();

  SpecialistDto getById(Long id);

}
