package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.SpecialistExpertiseAreaDto;

import java.util.List;

public interface SpecialistExpertiseAreaService {
//  SpecialistExpertiseAreaDto add(SpecialistExpertiseAreaDto specialistExpertiseAreaDto);
//

  List<SpecialistExpertiseAreaDto> getBySpecialistId(Long id);
  List<SpecialistExpertiseAreaDto> getByExpertiseAreaId(Long id);

}
