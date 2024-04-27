package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Review;
import com.example.cloudComputing.exception.review.*;
import com.example.cloudComputing.mapper.ReviewMapper;
import com.example.cloudComputing.serviceImpl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/review")
public class ReviewController {
  @Autowired
  private ReviewServiceImpl reviewServiceImpl;

  @Autowired
  ReviewMapper reviewMapper;

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/add")
  public ResponseEntity<String> addReview(@Valid @RequestBody ReviewDto reviewDto) {
      ResponseEntity.ok(reviewServiceImpl.add(reviewDto));
      return ResponseEntity.ok("{\"message\": \"Review added successful\"}");
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<ReviewDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(reviewServiceImpl.getReferenceById(id));
  }

  @GetMapping("/all")
  public List<ReviewDto> getAll() {
    return reviewServiceImpl.getAll();
  }

  @GetMapping("/givenBy/{id}")
  public ResponseEntity<List<ReviewDto>> getAllByGivenByUser(@PathVariable int id) {
    return ResponseEntity.ok(reviewServiceImpl.getAllByGivenByUser(id));
  }

  @GetMapping("/receivedBy/{id}")
  public ResponseEntity<List<ReviewDto>> getAllByReceivedBySpecialist(@PathVariable int id) {
    return ResponseEntity.ok(reviewServiceImpl.getAllByReceivedBySpecialist(id));
  }

  @GetMapping("/getAllByStars/{stars}")
  public ResponseEntity<List<ReviewDto>> getAllByStars(@PathVariable int stars) {
    return ResponseEntity.ok(reviewServiceImpl.getAllByStars(stars));
  }

  @GetMapping("/getAllByStarsBefore/{stars}")
  public ResponseEntity<List<ReviewDto>> getAllByStarsBefore(@PathVariable int stars) {
    return ResponseEntity.ok(reviewServiceImpl.getAllByStarsBefore(stars));
  }

  @GetMapping("/getAllByStarsAfter/{stars}")
  public ResponseEntity<List<ReviewDto>> getAllByStarsAfter(@PathVariable int stars) {
    return ResponseEntity.ok(reviewServiceImpl.getAllByStarsAfter(stars));
  }

}
