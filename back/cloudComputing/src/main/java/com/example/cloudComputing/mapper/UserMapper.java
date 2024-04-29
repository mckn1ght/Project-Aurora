package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.Resource;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
  @Autowired
  private ResourceRepository resourceRepository;
//  public User mapToUser(UserDto userDto) {
//    int userId = userDto.getId() != null ? userDto.getId().intValue() : 0; // Convert Long to int
//
//
//    User user =  User.builder()
//      .id(userDto.getId())
//      .username(userDto.getUsername())
//      .password(userDto.getPassword())
//      .firstName(userDto.getFirstName())
//      .lastName(userDto.getLastName())
//      .address(userDto.getAddress())
//      .email(userDto.getEmail())
//      .phoneNumber(userDto.getPhoneNumber())
//      .created(userDto.getCreated())
//      .last_updated(userDto.getLast_updated())
//      .credit(userDto.getCredit())
//      .isAdmin(userDto.isAdmin())
//      .specialistId(userDto.getSpecialistId())
//      .build();
//
//    // Create the default resource entry
//    Resource defaultResource = new Resource();
//    defaultResource.setUserId(userId);
//    defaultResource.setType("avatar");
//    defaultResource.setLocation("../../assets/users/defaultAvatar.png"); // Example path
//    defaultResource.setCreatedAt(new Date()); // Set creation and update time
//    defaultResource.setUpdatedAt(new Date());
//    resourceRepository.save(defaultResource);
//
//  return user;
//  }
public User mapToUser(UserDto userDto) {
  return User.builder()
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

  public void createDefaultResource(User user) {
    Resource defaultResource = new Resource();
    defaultResource.setUserId(user.getId().intValue()); // Assuming User ID is Long and Resource UserId is int
    defaultResource.setType("avatar");
    defaultResource.setLocation("uploads/avatars/defaultAvatar.png");
    defaultResource.setCreatedAt(new Date());
    defaultResource.setUpdatedAt(new Date());
    resourceRepository.save(defaultResource);
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
