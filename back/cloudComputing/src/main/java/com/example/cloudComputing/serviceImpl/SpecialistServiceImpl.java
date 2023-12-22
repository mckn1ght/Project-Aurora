package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.exception.specialist.NoSpecialistCoverageAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistExpertiseAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistIdFoundException;
import com.example.cloudComputing.mapper.SpecialistMapper;
import com.example.cloudComputing.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class SpecialistServiceImpl implements SpecialistService {

  @Autowired
  private SpecialistRepository specialistRepository;

  @Autowired
  private SpecialistMapper specialistMapper;

  public SpecialistDto add(SpecialistDto specialistDto) {
    return specialistMapper.mapToSpecialistDto(specialistRepository.save(specialistMapper.mapToSpecialist(specialistDto)));
  }

  public List<SpecialistDto> getAll() {
    List<SpecialistDto> specialistDtos = specialistRepository.findAll().stream().map(specialist -> specialistMapper.mapToSpecialistDto(specialist)).collect(Collectors.toList());
    if (specialistDtos.isEmpty()) {
      throw new NoSpecialistFoundException(NO_SPECIALIST_FOUND);
    }
    return specialistDtos;
  }

  public SpecialistDto getById(Long id) {
    try {
      Specialist specialist = specialistRepository.getReferenceById(id);
      return specialistMapper.mapToSpecialistDto(specialist);
    }catch(Exception e){
      throw new NoSpecialistIdFoundException(String.format(NO_SPECIALIST_ID_FOUND, id));
    }
  }

  public List<SpecialistDto> getAllByExpertiseArea(int expertiseArea) {
    List<SpecialistDto> specialistDtos = specialistRepository.getAllByExpertiseArea(expertiseArea).stream()
      .map(specialist -> specialistMapper.mapToSpecialistDto(specialist)).collect(Collectors.toList());
    if (specialistDtos.isEmpty()) {
      throw new NoSpecialistExpertiseAreaFoundException(String.format(NO_SPECIALIST_EXPERTISE_AREA_FOUND, expertiseArea));
    }
    return specialistDtos;
  }

  public List<SpecialistDto> getAllByCoverageArea(String coverageArea) {
    List<SpecialistDto> specialistDtos = specialistRepository.getAllByCoverageArea(coverageArea).stream()
      .map(specialist -> specialistMapper.mapToSpecialistDto(specialist)).collect(Collectors.toList());
    if (specialistDtos.isEmpty()) {
      throw new NoSpecialistCoverageAreaFoundException(String.format(NO_SPECIALIST_COVERAGE_AREA_FOUND, coverageArea));
    }
    return specialistDtos;
  }

  public List<SpecialistDto> getAllByExpertiseAreaAndCoverageArea(int expertiseArea, String coverageArea) {
    List<SpecialistDto> specialistDtos = specialistRepository.getAllByExpertiseAreaAndCoverageArea(expertiseArea, coverageArea).stream()
      .map(specialist -> specialistMapper.mapToSpecialistDto(specialist)).collect(Collectors.toList());
    if (specialistDtos.isEmpty()) {
      throw new NoSpecialistCoverageAreaFoundException(String.format(NO_SPECIALIST_COVERAGE_AREA_FOUND, coverageArea));
    }
    return specialistDtos;
  }
}



