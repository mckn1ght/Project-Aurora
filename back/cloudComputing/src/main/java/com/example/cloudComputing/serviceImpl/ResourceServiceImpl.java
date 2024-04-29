package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.dto.SpecialistDto;
import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.Resource;
import com.example.cloudComputing.exception.bid.NoBidIdFoundException;
import com.example.cloudComputing.exception.resource.*;
import com.example.cloudComputing.exception.specialist.NoSpecialistCoverageAreaFoundException;
import com.example.cloudComputing.mapper.ResourceMapper;
import com.example.cloudComputing.repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private ResourceMapper resourceMapper;



  public ResourceDto add(ResourceDto resourceDto) {
    return resourceMapper.mapToResourceDto(resourceRepository.save(resourceMapper.mapToResource(resourceDto)));
  }

  public List<ResourceDto> getAll() {
    List<ResourceDto> resourceDtos = resourceRepository.findAll().stream().map(resource -> resourceMapper.mapToResourceDto(resource)).collect(Collectors.toList());
    if (resourceDtos.isEmpty()) {
      throw new NoResourceFoundException(NO_RESOURCE_FOUND);
    }
    return resourceDtos;
  }

  public ResourceDto getById(Long id) {
    Resource resource = resourceRepository.getById(id);
    if (resource == null) {
      throw new NoResourceFoundException(NO_RESOURCE_FOUND);
    }
    return resourceMapper.mapToResourceDto(resource);
  }

  public List<ResourceDto> findAllByUserId(int userId){
    List<ResourceDto> resourceDtos = resourceRepository.findAllByUserId(userId).stream().map(resource -> resourceMapper.mapToResourceDto(resource)).collect(Collectors.toList());
    if (resourceDtos.isEmpty()) {
      throw new NoResourceFoundException(NO_RESOURCE_FOUND);
    }
    return resourceDtos;
  }

  public ResourceDto findByUserIdAndAndType(int userId, String type){
    Resource resource = resourceRepository.findByUserIdAndAndType(userId, type);
    if(resource == null){
      throw new NoResourceFoundException(NO_RESOURCE_FOUND);
    }
    return resourceMapper.mapToResourceDto(resource);
  }

  public void updateResource(ResourceDto resourceDto) {
    Resource resource = resourceMapper.mapToResource(resourceDto);
    resourceRepository.save(resource);
  }

  public void updateAvatarResource(int userId, String location, String type) {
    Resource resource = resourceRepository.findByUserIdAndAndType(userId, type);

    if (resource != null) {
      // Update existing resource
      resource.setLocation(location);
    } else {
      // Create new resource if none exists
      resource = new Resource();
      resource.setUserId(userId);
      resource.setType(type);
      resource.setLocation(location);
    }

    resourceRepository.save(resource);
  }

}



