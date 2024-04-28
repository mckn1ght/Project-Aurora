package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.Resource;
import com.example.cloudComputing.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceMapper {
  public Resource mapToResource(ResourceDto resourceDto) {
    return Resource.builder()
      .id(resourceDto.getId())
      .userId(resourceDto.getUserId())
      .type(resourceDto.getType())
      .location(resourceDto.getLocation())
      .createdAt(resourceDto.getCreatedAt())
      .updatedAt(resourceDto.getUpdatedAt())
      .build();
  }

  public ResourceDto mapToResourceDto(Resource resource) {
    return ResourceDto.builder()
      .id(resource.getId())
      .userId(resource.getUserId())
      .type(resource.getType())
      .location(resource.getLocation())
      .createdAt(resource.getCreatedAt())
      .updatedAt(resource.getUpdatedAt())
      .build();
  }

}
