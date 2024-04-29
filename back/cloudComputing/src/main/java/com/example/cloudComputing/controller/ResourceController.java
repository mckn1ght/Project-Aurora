package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.entity.Resource;
import com.example.cloudComputing.mapper.ResourceMapper;
import com.example.cloudComputing.serviceImpl.ResourceServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/resource")
public class ResourceController {
  @Autowired
  private ResourceServiceImpl resourceServiceImpl;

  @Autowired
  ResourceMapper resourceMapper;
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> add(@Valid @RequestBody ResourceDto resourceDto) {
      ResponseEntity.ok(resourceServiceImpl.add(resourceDto));
      return ResponseEntity.ok("{\"message\": \"Resource added succesfully/\"}");

  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<ResourceDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(resourceServiceImpl.getById(id));
  }

  @GetMapping("/all")
  public List<ResourceDto> getAll() {
    return resourceServiceImpl.getAll();
  }

  @GetMapping("/getByUserId/{userId}")
  public List<ResourceDto> findAllByUserId(@PathVariable int userId){
    return resourceServiceImpl.findAllByUserId(userId);
  }

  @GetMapping("/getByUserId/{userId}/type/{type}")
  public ResourceDto findByUserIdAndAndType(@PathVariable int userId, @PathVariable String type){
    return resourceServiceImpl.findByUserIdAndAndType(userId, type);
  }

//  @PostMapping("/users/{userId}/upload-avatar")
//  public ResponseEntity<String> uploadUserAvatar(@PathVariable int userId, @RequestParam("avatar") MultipartFile file) {
//    if (file.isEmpty()) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot upload empty file");
//    }
//
//    try {
//      String directory = "uploads/avatars";
//      Path uploadPath = Paths.get(directory);
//
//      if (!Files.exists(uploadPath)) {
//        Files.createDirectories(uploadPath);
//      }
//
//      Path filePath = uploadPath.resolve(file.getOriginalFilename());
//      file.transferTo(filePath);
//
//      String fileLocation = filePath.toString();
//
//      // Update the avatar location using the service
//      resourceServiceImpl.updateAvatarResource(userId, fileLocation, "avatar");
//
//      return ResponseEntity.ok("File uploaded successfully: " + fileLocation);
//    } catch (IOException e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
//    }
//  }


//  @PostMapping("/users/{userId}/upload-avatar")
//  public ResponseEntity<String> uploadUserAvatar(@PathVariable int userId, @RequestParam("avatar") MultipartFile file) {
//    if (file.isEmpty()) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot upload empty file");
//    }
//
//    try {
//      String directory = "uploads/avatars";
//      Path uploadPath = Paths.get(directory);
//
//      if (!Files.exists(uploadPath)) {
//        Files.createDirectories(uploadPath);
//      }
//
//      // Generate a new filename using the user ID and current timestamp to ensure uniqueness
//      String extension = getExtension(file.getOriginalFilename());
//      String newFilename = userId + "_" + Instant.now().toEpochMilli() + (extension.isEmpty() ? "" : "." + extension);
//
//      Path filePath = uploadPath.resolve(newFilename);
//      file.transferTo(filePath);
//
//      String fileLocation = filePath.toString();
//
//      // Update the avatar location using the service
//      resourceServiceImpl.updateAvatarResource(userId, fileLocation, "avatar");
//
//      return ResponseEntity.ok("File uploaded and renamed successfully: " + fileLocation);
//    } catch (IOException e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
//    }
//  }

  @PostMapping("/users/{userId}/upload-avatar")
  public ResponseEntity<String> uploadUserAvatar(@PathVariable int userId, @RequestParam("avatar") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot upload empty file");
    }

    try {
      String directory = "uploads/avatars";
      Path uploadPath = Paths.get(directory);

      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      // Retrieve the current avatar's filename from the database
      ResourceDto currentAvatar = resourceServiceImpl.findByUserIdAndAndType(userId, "avatar");
      if (currentAvatar != null && currentAvatar.getLocation() != null && !currentAvatar.getLocation().endsWith("defaultAvatar.png")) {
        Path oldFilePath = Paths.get(currentAvatar.getLocation()); // Correctly create the path to the old file
        try {
          Files.deleteIfExists(oldFilePath); // Attempt to delete old file
          System.out.println("Deleted old avatar: " + oldFilePath);
        } catch (IOException ex) {
          System.err.println("Failed to delete old avatar: " + oldFilePath + " with error: " + ex.getMessage());
          // Additional handling or logging here
        }
      }

      // Generate a new filename using the user ID and current timestamp to ensure uniqueness
      String extension = getExtension(file.getOriginalFilename());
      String newFilename = userId + "_" + Instant.now().toEpochMilli() + (extension.isEmpty() ? "" : "." + extension);

      Path filePath = uploadPath.resolve(newFilename);
      file.transferTo(filePath);

      String fileLocation = filePath.toString();

      // Update the avatar location using the service
      resourceServiceImpl.updateAvatarResource(userId, fileLocation, "avatar");

      return ResponseEntity.ok("File uploaded and renamed successfully: " + fileLocation);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
    }
  }




  private String getExtension(String filename) {
    int lastIndex = filename.lastIndexOf('.');
    if (lastIndex == -1) {
      return ""; // empty extension
    }
    return filename.substring(lastIndex + 1);
  }





}

