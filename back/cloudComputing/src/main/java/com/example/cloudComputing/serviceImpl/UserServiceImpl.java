package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.exception.user.*;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;


  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


  public UserDto add(UserDto userDto) {
    return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
  }


  public List<UserDto> getAll() {
    List<UserDto> userDtos = userRepository.findAll().stream().map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDtos.isEmpty()) {
      throw new NoUserFoundException(NO_USER_FOUND);
    }
    return userDtos;
  }


  public UserDto getById(Long id) {
    User user = userRepository.getById(id);
    if (user == null) {
      throw new UserIdNotFoundException(String.format(NO_USER_ID_FOUND, id));
    }
    return userMapper.mapToUserDto(user);

  }

  public UserDto getByUsername(String username) {
    User user = userRepository.getByUsername(username);
    if (user == null) {
      throw new UserUsernameNotFoundException(String.format(NO_USER_USERNAME_FOUND, username));
    }
    return userMapper.mapToUserDto(user);

  }

  public List<UserDto> getAllByPermissions(String permission) {
    List<UserDto> userDto = userRepository.getAllByPermissions(permission).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {

      throw new NoUserPermissionFoundException(String.format(NO_USER_PERMISSION_FOUND, permission));
    }
    return userDto;

  }

  public List<UserDto> getAllByCreditAfter(double credit) {
    List<UserDto> userDto = userRepository.getAllByCreditAfter(credit).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {
      throw new NoCreditHigherFoundException(String.format(NO_USER_WITH_CREDIT_HIGHER_THAN, credit));
    }
    return userDto;
  }

  public List<UserDto> getAllByCreditBefore(double credit) {
    List<UserDto> userDto = userRepository.getAllByCreditBefore(credit).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {
      throw new NoCreditLowerFoundException(String.format(NO_USER_WITH_CREDIT_LOWER_THAN, credit));
    }
    return userDto;
  }

  public boolean authenticate(String username, String storedHashedPassword) {
    User user = userRepository.getByUsername(username);
    return user != null && passwordEncoder.matches(storedHashedPassword, user.getPassword());

  }


}



