package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.exception.user.UserUsernameNotFoundException;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.serviceImpl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserServiceImpl userServiceImpl;

  @Autowired
  UserMapper userMapper;
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> adduser(@Valid @RequestBody UserDto userDto) {
    if (userServiceImpl.existsUserByUsername(userDto.getUsername())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken.");
    }
    else if(userServiceImpl.existsUserByEmail(userDto.getEmail())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already taken.");
    }
    else if(userServiceImpl.existsUserByPhoneNumber(userDto.getPhoneNumber())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("The phone number is already in use.");
    }
    else {
      ResponseEntity.ok(userServiceImpl.add(userDto));
      return ResponseEntity.ok("{\"message\": \"Account registered successful\"}");
    }
  }

  @PutMapping(path = "/updatePhoneNumber/{id}", consumes = "application/json")
  @Transactional
  public ResponseEntity<Void> updatePhoneNumber(
    @PathVariable Long id,
    @RequestBody Map<String, String> phoneNumberMap
  ) {
    String phoneNumber = phoneNumberMap.get("phoneNumber");
    System.out.println("Received phone number: " + phoneNumber);
    userServiceImpl.updatePhoneNumber(id, phoneNumber);
    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/updateEmail/{id}", consumes = "application/json")
  @Transactional
  public ResponseEntity<Void> updateEmail(
    @PathVariable Long id,
    @RequestBody Map<String, String> emailMap
  ) {
    String email = emailMap.get("email");
    System.out.println("Received email: " + email);
    userServiceImpl.updateEmail(id, email);
    return ResponseEntity.ok().build();
  }



  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping("/getById/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<UserDto> getAll() {
    return userServiceImpl.getAll();
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/username/{username}")
  public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userServiceImpl.getByUsername(username));
  }

  @GetMapping("/isAdmin/{isAdmin}")
  public ResponseEntity<List<UserDto>> getAllByPermission(@PathVariable boolean isAdmin) {
    return ResponseEntity.ok(userServiceImpl.getAllByIsAdmin(isAdmin));
  }

  @GetMapping("/creditHigher/{value}")
  public ResponseEntity<List<UserDto>> getAllByCreditAfter(@PathVariable double value) {
    return ResponseEntity.ok(userServiceImpl.getAllByCreditAfter(value));
  }


  @GetMapping("/creditSmaller/{value}")
  public ResponseEntity<List<UserDto>> getAllByCreditBefore(@PathVariable double value) {
    return ResponseEntity.ok(userServiceImpl.getAllByCreditBefore(value));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserDto userDto) {
    try {
      UserDto user = userServiceImpl.getByUsername(userDto.getUsername());

      boolean isAuthenticated = userServiceImpl.authenticate(user.getUsername(), userDto.getPassword());

      if (isAuthenticated) {
        return ResponseEntity.ok("{\"message\": \"Authentication successful\"}");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Invalid credentials\"}");
      }
    } catch (UserUsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
    }
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping("/getBySpecialistId/{id}")
  public ResponseEntity<UserDto> getBySpecialistId(@PathVariable int id) {
    return ResponseEntity.ok(userServiceImpl.getBySpecialistId(id));
  }
}



