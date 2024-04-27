package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobExpertiseAreaDto;
import com.example.cloudComputing.dto.SpecialistExpertiseAreaDto;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaFoundException;
import com.example.cloudComputing.exception.job.NoJobFoundException;
import com.example.cloudComputing.mapper.SpecialistExpertiseAreaMapper;
import com.example.cloudComputing.repository.SpecialistExpertiseAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class SpecialistExpertiseAreaServiceImpl implements SpecialistExpertiseAreaService {

  @Autowired
  private SpecialistExpertiseAreaRepository specialistExpertiseAreaRepository;

  @Autowired
  private SpecialistExpertiseAreaMapper specialistExpertiseAreaMapper;

  public SpecialistExpertiseAreaDto add(SpecialistExpertiseAreaDto specialistExpertiseAreaDto) {
    return specialistExpertiseAreaMapper.mapToSpecialistExpertiseAreaDto(specialistExpertiseAreaRepository.save(specialistExpertiseAreaMapper.mapToSpecialistExpertiseArea(specialistExpertiseAreaDto)));
  }

  public List<SpecialistExpertiseAreaDto> getAll() {
    List<SpecialistExpertiseAreaDto> specialistExpertiseAreaDtos = specialistExpertiseAreaRepository.findAll().stream().map(specialistExpertiseAreaDto ->
      specialistExpertiseAreaMapper.mapToSpecialistExpertiseAreaDto(specialistExpertiseAreaDto)).collect(Collectors.toList());
    if (specialistExpertiseAreaDtos.isEmpty()) {
      throw new NoJobFoundException(NO_SPECIALIST_FOUND);
    }
    return specialistExpertiseAreaDtos;
  }

  public List<SpecialistExpertiseAreaDto> getByExpertiseAreaId(Long expertiseAreaid) {
    List<SpecialistExpertiseAreaDto> specialistExpertiseAreaDtos = specialistExpertiseAreaRepository.getByExpertiseAreaId(expertiseAreaid).stream().map(specialistExpertiseArea ->
      specialistExpertiseAreaMapper.mapToSpecialistExpertiseAreaDto(specialistExpertiseArea)).collect(Collectors.toList());
    if (specialistExpertiseAreaDtos.isEmpty()) {
      throw new NoExpertiseAreaFoundException(String.format(NO_EXPAREA_ID_FOUND, expertiseAreaid));
    }
    return specialistExpertiseAreaDtos;
  }

  public List<SpecialistExpertiseAreaDto> getBySpecialistId(Long specialistId) {
      List<SpecialistExpertiseAreaDto> specialistExpertiseAreaDtos =  specialistExpertiseAreaRepository.getBySpecialistId(specialistId).stream().map(specialistExpertiseArea ->
        specialistExpertiseAreaMapper.mapToSpecialistExpertiseAreaDto(specialistExpertiseArea)).collect(Collectors.toList());
      if (specialistExpertiseAreaDtos.isEmpty()) {
        throw new NoExpertiseAreaFoundException(String.format(NO_JOB_ID_FOUND, specialistId));
      }
      return specialistExpertiseAreaDtos;
    }

}

