package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.JobPictureDto;
import com.example.cloudComputing.entity.JobPicture;
import com.example.cloudComputing.mapper.JobPictureMapper;
import com.example.cloudComputing.repository.JobPictureRepository;
import com.example.cloudComputing.serviceImpl.JobPictureServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/jobPicture")
public class JobPictureController {
  @Autowired
  private JobPictureServiceImpl jobPictureServiceImpl;

  @Autowired
  JobPictureMapper jobPictureMapper;

  @Autowired
  private JobPictureRepository jobPictureRepository;

  private static final String UPLOAD_DIR = "uploads/jobs/";

//  @PostMapping("/upload/{jobId}")
//  public ResponseEntity<String> uploadJobPictures(@PathVariable int jobId, @RequestParam("pictures") MultipartFile[] pictures) {
//    System.out.println("Uploading pictures for job ID: " + jobId); // Log job ID
//    try {
//      for (int i = 0; i < pictures.length; i++) {
//        MultipartFile picture = pictures[i];
//        System.out.println("Processing file: " + picture.getOriginalFilename()); // Log file name
//        JobPictureDto jobPictureDto = new JobPictureDto();
//        jobPictureDto.setJobId(jobId);
//        jobPictureDto.setUrl(saveFile(jobId, picture, i + 1)); // Implement saveFile method to save the file and return the URL
//        jobPictureServiceImpl.add(jobPictureDto);
//      }
//      return ResponseEntity.ok("Pictures uploaded successfully");
//    } catch (IOException e) {
//      System.err.println("Error uploading pictures: " + e.getMessage()); // Log error
//      return ResponseEntity.status(500).body("Failed to upload pictures: " + e.getMessage());
//    } catch (Exception e) {
//      System.err.println("General error: " + e.getMessage()); // Log general error
//      return ResponseEntity.status(500).body("General error: " + e.getMessage());
//    }
//  }

  @GetMapping("/jobId/{jobId}")
  public ResponseEntity<List<JobPictureDto>> getAllByJobId(@PathVariable int jobId) {
    return ResponseEntity.ok(jobPictureServiceImpl.getAllByJobId(jobId));
  }

  @PostMapping("/upload/{jobId}")
  public ResponseEntity<String> uploadJobPictures(@PathVariable int jobId, @RequestParam("pictures") MultipartFile[] pictures) {
    System.out.println("Uploading pictures for job ID: " + jobId); // Log job ID
    try {
      for (MultipartFile picture : pictures) {
        System.out.println("Processing file: " + picture.getOriginalFilename()); // Log file name
        JobPictureDto jobPictureDto = new JobPictureDto();
        jobPictureDto.setJobId(jobId);
        String url = saveFile(jobId, picture); // Implement saveFile method to save the file and return the URL
        if (!jobPictureServiceImpl.existsByUrlAndJobId(url, jobId)) { // Check if URL already exists for the job
          jobPictureDto.setUrl(url);
          jobPictureServiceImpl.add(jobPictureDto);
        } else {
          System.out.println("Duplicate picture detected: " + url); // Log duplicate
        }
      }
      return ResponseEntity.ok("Pictures uploaded successfully");
    } catch (IOException e) {
      System.err.println("Error uploading pictures: " + e.getMessage()); // Log error
      return ResponseEntity.status(500).body("Failed to upload pictures: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("General error: " + e.getMessage()); // Log general error
      return ResponseEntity.status(500).body("General error: " + e.getMessage());
    }
  }

  private String saveFile(int jobId, MultipartFile file) throws IOException {
    // Get the application's base directory
    String baseDir = System.getProperty("user.dir");

    // Ensure the upload directory exists
    File uploadDir = new File(baseDir, UPLOAD_DIR);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }

    // Generate the file name with a UUID to ensure uniqueness
    String extension = getFileExtension(file.getOriginalFilename());
    String fileName = "JOB" + jobId + "-picture" + UUID.randomUUID() + extension;
    String filePath = Paths.get(uploadDir.getAbsolutePath(), fileName).toString();
    File dest = new File(filePath);
    file.transferTo(dest);
    System.out.println("File saved to: " + filePath); // Log file path

    // Return the relative URL
    return UPLOAD_DIR + fileName; // This should be the relative path accessible by the frontend
  }

  private String getFileExtension(String fileName) {
    if (fileName == null) {
      return "";
    }
    int dotIndex = fileName.lastIndexOf('.');
    return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteJobPicture(@RequestParam String url) {
    try {
      System.out.println("Received URL for deletion: " + url);
      String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
      System.out.println("Decoded URL for deletion: " + decodedUrl);
      boolean isDeleted = jobPictureServiceImpl.deleteJobPictureByUrl(decodedUrl);
      if (isDeleted) {
        return ResponseEntity.ok("Picture deleted successfully");
      } else {
        return ResponseEntity.status(404).body("Picture not found");
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to delete picture: " + e.getMessage());
    }
  }
}
