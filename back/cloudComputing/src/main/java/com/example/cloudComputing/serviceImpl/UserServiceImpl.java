package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.exception.user.*;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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


//  public UserDto add(UserDto userDto) {
//    return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
//  }
@Transactional
public UserDto add(UserDto userDto) {
  User user = userRepository.save(userMapper.mapToUser(userDto));
  userMapper.createDefaultResource(user); // Handle resource creation after the user is saved
  return userMapper.mapToUserDto(user);
}
  @Transactional
  public void updatePhoneNumber(Long userId, String phoneNumber) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new NoUserFoundException(String.format(NO_USER_ID_FOUND, userId)));
    System.out.println("Current phone number: " + user.getPhoneNumber()); // Log current number
    user.setPhoneNumber(phoneNumber);
    userRepository.save(user);
    System.out.println("Updated phone number: " + user.getPhoneNumber()); // Log updated number
  }

  @Transactional
  public void updateCredit(Long userId, double credit) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new NoUserFoundException(String.format(NO_USER_ID_FOUND, userId)));
    System.out.println("Current credit: " + user.getCredit());
    user.setCredit(credit);
    userRepository.save(user);
    System.out.println("Updated credit: " + user.getCredit());
  }


  @Transactional
  public void updateEmail(Long userId, String email) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new NoUserFoundException(String.format(NO_USER_ID_FOUND, userId)));
    System.out.println("Current email: " + user.getEmail()); // Log current email
    user.setEmail(email);
    userRepository.save(user);
    System.out.println("Updated email to: " + user.getEmail()); // Log updated email
  }

  public boolean existsUserByUsername(String username) {
    return userRepository.existsUserByUsername(username);
  }

  public boolean existsUserByEmail(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public boolean existsUserByPhoneNumber(String phoneNumber){ return userRepository.existsUserByPhoneNumber(phoneNumber); }

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

  public List<UserDto> getAllByIsAdmin(boolean isAdmin) {
    List<UserDto> userDto = userRepository.getAllByIsAdmin(isAdmin).stream()
      .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
    if (userDto.isEmpty()) {

      throw new NoUserIsAdminFoundException(String.format(NO_USER_ISADMIN_FOUND, isAdmin));
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

  public UserDto getBySpecialistId(int id) {
    User user = userRepository.getBySpecialistId(id);
    if (user == null) {
      throw new UserIdNotFoundException(String.format(NO_USER_SPECIALIST_ID_FOUND, id));
    }
    return userMapper.mapToUserDto(user);
  }

  public User updateUserDetails(User user) throws Exception {
    User existingUser = userRepository.findById(user.getId())
      .orElseThrow(() -> new Exception("User not found: " + user.getId()));
    existingUser.setUsername(user.getUsername());
    existingUser.setEmail(user.getEmail());
    existingUser.setPhoneNumber(user.getPhoneNumber());
    existingUser.setCredit(user.getCredit()); // Assuming you want to update credit too.
    return userRepository.save(existingUser);
  }


}



