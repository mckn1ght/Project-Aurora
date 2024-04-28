package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.dto.UserDto;

import java.util.List;

public interface ResourceService {
  ResourceDto add(ResourceDto resourceDto);

  List<ResourceDto> getAll();
}
