package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.exception.user.UserUsernameNotFoundException;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
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
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
    }
    else if(userServiceImpl.existsUserByEmail(userDto.getEmail())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken.");
    }
    else {
      ResponseEntity.ok(userServiceImpl.add(userDto));
      return ResponseEntity.ok("{\"message\": \"Account registered successful\"}");
    }
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<UserDto> getAll() {
    return userServiceImpl.getAll();
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userServiceImpl.getByUsername(username));
  }

  @GetMapping("/permissions/{permissions}")
  public ResponseEntity<List<UserDto>> getAllByPermissions(@PathVariable int permissions) {
    return ResponseEntity.ok(userServiceImpl.getAllByPermissions(permissions));
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
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid credentials\"}");
      }
    } catch (UserUsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + e.getMessage() + "\"}");
    }
  }

}



