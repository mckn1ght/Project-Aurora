package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.ExpertiseAreaDto;
import com.example.cloudComputing.entity.ExpertiseArea;
import com.example.cloudComputing.exception.expertiseArea.*;
import com.example.cloudComputing.mapper.ExpertiseAreaMapper;
import com.example.cloudComputing.repository.ExpertiseAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class ExpertiseAreaServiceImpl implements ExpertiseAreaService {

  @Autowired
  private ExpertiseAreaRepository expertiseAreaRepository;

  @Autowired
  private ExpertiseAreaMapper expertiseAreaMapper;

  public ExpertiseAreaDto add(ExpertiseAreaDto expertiseAreaDto) {
    return expertiseAreaMapper.mapToExpertiseAreaDto(expertiseAreaRepository.save(expertiseAreaMapper.mapToExpertiseArea(expertiseAreaDto)));
  }

  public List<ExpertiseAreaDto> getAll() {
    List<ExpertiseAreaDto> expertiseAreaDtos = expertiseAreaRepository.findAll().stream().map(expertiseArea ->
      expertiseAreaMapper.mapToExpertiseAreaDto(expertiseArea)).collect(Collectors.toList());
    if (expertiseAreaDtos.isEmpty()) {
      throw new NoExpertiseAreaFoundException(NO_EXPAREA_FOUND);
    }
    return expertiseAreaDtos;
  }

  public ExpertiseAreaDto getReferenceById(Long id) {
    try {
      ExpertiseArea expertiseArea = expertiseAreaRepository.getReferenceById(id);
      return expertiseAreaMapper.mapToExpertiseAreaDto(expertiseArea);
    }catch(Exception e){
      throw new NoExpertiseAreaIdFoundException(String.format(NO_EXPAREA_ID_FOUND, id));
    }
  }

  public ExpertiseAreaDto getByName(String name) {
    try {
      ExpertiseArea expertiseArea = expertiseAreaRepository.getByName(name);
      return expertiseAreaMapper.mapToExpertiseAreaDto(expertiseArea);
    }catch(Exception e){
      throw new NoExpertiseAreaNameFoundException(String.format(NO_EXPAREA_NAME_FOUND, name));
    }
  }

  public List<ExpertiseAreaDto> getAllByNameContainingIgnoreCase(String name) {
    List<ExpertiseAreaDto> expertiseAreaDtos = expertiseAreaRepository.getAllByNameContainingIgnoreCase(name).stream()
      .map(expertiseArea -> expertiseAreaMapper.mapToExpertiseAreaDto(expertiseArea)).collect(Collectors.toList());
    if (expertiseAreaDtos.isEmpty()) {
      throw new NoExpertiseAreaNameFoundException(String.format(NO_EXPAREA_NAME_FOUND, name));
    }
    return expertiseAreaDtos;
  }

}

