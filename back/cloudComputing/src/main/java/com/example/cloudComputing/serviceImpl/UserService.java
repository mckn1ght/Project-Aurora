package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto add(UserDto userDto);

  List<UserDto> getAll();

  UserDto getById(Long id);

  UserDto getByUsername(String username);

  List<UserDto> getAllByPermissions(int permission);

  List<UserDto> getAllByCreditAfter(double credit);

  List<UserDto> getAllByCreditBefore(double credit);
}
