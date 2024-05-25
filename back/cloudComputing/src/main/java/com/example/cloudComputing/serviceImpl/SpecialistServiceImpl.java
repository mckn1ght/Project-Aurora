package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.exception.specialist.NoSpecialistCoverageAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistExpertiseAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistIdFoundException;
import com.example.cloudComputing.mapper.SpecialistMapper;
import com.example.cloudComputing.repository.ExpertiseAreaRepository;
import com.example.cloudComputing.repository.SpecialistRepository;
import com.example.cloudComputing.repository.UserRepository;
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

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ExpertiseAreaRepository expertiseAreaRepository;


  public Specialist add(Long userId) {
    Specialist specialist = new Specialist();
    specialist.setRating(5);
    Specialist savedSpecialist = specialistRepository.save(specialist);

    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    user.setSpecialistId(Math.toIntExact(savedSpecialist.getId().intValue()));
    userRepository.save(user);

    return savedSpecialist;
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

  public List<String> getExpertiseAreaNamesBySpecialistId(Long specialistId) {
    return expertiseAreaRepository.findExpertiseAreaNamesBySpecialistId(specialistId);}


}



