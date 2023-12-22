package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Review;

import java.util.List;

public interface ReviewService {
  ReviewDto add(ReviewDto reviewDto);

  List<ReviewDto> getAll();

  ReviewDto getReferenceById(Long id);

  List<ReviewDto> getAllByGivenByUser(int id);

  List<ReviewDto> getAllByReceivedBySpecialist(int id);

  List<ReviewDto> getAllByStars(int stars);

  List<ReviewDto> getAllByStarsBefore(int stars);

  List<ReviewDto> getAllByStarsAfter(int stars);

}
