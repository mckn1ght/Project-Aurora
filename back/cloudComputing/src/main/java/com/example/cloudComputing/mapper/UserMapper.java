package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User mapToUser(UserDto userDto) {
    return User.builder()
      .id(userDto.getId())
      .username(userDto.getUsername())
      .password(userDto.getPassword())
      .address(userDto.getAddress())
      .email(userDto.getEmail())
      .created(userDto.getCreated())
      .last_updated(userDto.getLast_updated())
      .credit(userDto.getCredit())
      .permission(userDto.getPermission())
      .build();
  }

  public UserDto mapToUserDto(User user) {
    return UserDto.builder()
      .id(user.getId())
      .username(user.getUsername())
      .password(user.getPassword())
      .address(user.getAddress())
      .email(user.getEmail())
      .created(user.getCreated())
      .last_updated(user.getLast_updated())
      .credit(user.getCredit())
      .permission(user.getPermission())
      .build();
  }
}
