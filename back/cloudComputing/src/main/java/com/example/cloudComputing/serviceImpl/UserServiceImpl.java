package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public UserDto add(UserDto userDto) {
    return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
  }


  public List<UserDto> getAll() {
    List<UserDto> userDtos = userRepository.findAll().stream().map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDtos.isEmpty()) {
      throw new NoSuchElementException("No users found");
    }
    return userDtos;
  }


  public UserDto getById(Long id) {
    User user = userRepository.getById(id);
    if (user == null) {
      throw new NoSuchElementException("User id does not exist");
    }
    return userMapper.mapToUserDto(user);

  }

  public UserDto getByUsername(String username) {
    User user = userRepository.getByUsername(username);
    if (user == null) {
      throw new NoSuchElementException("Username does not exist");
    }
    return userMapper.mapToUserDto(user);

  }

  public List<UserDto> getAllByPermissions(String permission) {
    List<UserDto> userDto = userRepository.getAllByPermissions(permission).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {
      throw new NoSuchElementException("Permission does not exist");
    }
    return userDto;

  }

  public List<UserDto> getAllByCreditAfter(double credit) {
    List<UserDto> userDto = userRepository.getAllByCreditAfter(credit).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {
      throw new NoSuchElementException("No entry bigger than " + credit + "found");
    }
    return userDto;
  }

  public List<UserDto> getAllByCreditBefore(double credit) {
    List<UserDto> userDto = userRepository.getAllByCreditBefore(credit).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {
      throw new NoSuchElementException("No entry smaller than " + credit + "found");
    }
    return userDto;
  }

  public boolean authenticate(String username, String password) {
    User user = userRepository.getByUsername(username);
    return user != null && user.getPassword().equals(password);
  }

}



