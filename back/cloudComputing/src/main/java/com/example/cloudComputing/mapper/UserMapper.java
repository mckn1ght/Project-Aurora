package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
  public User mapToUser(UserDto userDto) {
    return User.builder()
      .id(userDto.getId())
      .username(userDto.getUsername())
      .password(userDto.getPassword())
      .firstName(userDto.getFirstName())
      .lastName(userDto.getLastName())
      .address(userDto.getAddress())
      .email(userDto.getEmail())
      .phoneNumber(userDto.getPhoneNumber())
      .created(userDto.getCreated())
      .last_updated(userDto.getLast_updated())
      .credit(userDto.getCredit())
      .isAdmin(userDto.isAdmin())
      .specialistId(userDto.getSpecialistId())
      .build();
  }

  public UserDto mapToUserDto(User user) {
    return UserDto.builder()
      .id(user.getId())
      .username(user.getUsername())
      .password(user.getPassword())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .address(user.getAddress())
      .email(user.getEmail())
      .phoneNumber(user.getPhoneNumber())
      .created(user.getCreated())
      .last_updated(user.getLast_updated())
      .credit(user.getCredit())
      .isAdmin(user.isAdmin())
      .specialistId((user.getSpecialistId()))
      .build();
  }

  public List<UserDto> mapToUserDtoList(List<User> userList) {
    return userList.stream()
      .map(this::mapToUserDto)
      .collect(Collectors.toList());
  }
}
