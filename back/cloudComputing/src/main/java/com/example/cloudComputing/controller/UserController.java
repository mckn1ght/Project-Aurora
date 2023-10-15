package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserServiceImpl userServiceImpl;

  @Autowired
  UserMapper userMapper;

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<UserDto> adduser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userServiceImpl.add(userDto));
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userServiceImpl.getById(id));
  }

//  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/all")
  public List<UserDto> getAll() {
    return userServiceImpl.getAll();
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userServiceImpl.getByUsername(username));
  }

  @GetMapping("/permissions/{permissions}")
  public ResponseEntity<List<UserDto>> getAllByPermissions(@PathVariable String permissions) {
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
    boolean isAuthenticated = userServiceImpl.authenticate(userDto.getUsername(), userDto.getPassword());
    if (isAuthenticated) {
      return ResponseEntity.ok("{\"message\": \"Authentication successful\"}");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid credentials\"}");
    }

  }
}
