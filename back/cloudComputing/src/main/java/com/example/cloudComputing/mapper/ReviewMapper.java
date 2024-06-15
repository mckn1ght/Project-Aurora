package com.example.cloudComputing.mapper;


import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
  public Review mapToReview(ReviewDto reviewDto) {
    return Review.builder()
      .id(reviewDto.getId())
      .givenByUser(reviewDto.getGivenByUser())
      .receivedBySpecialist(reviewDto.getReceivedBySpecialist())
      .stars(reviewDto.getStars())
      .title(reviewDto.getTitle())
      .description(reviewDto.getDescription())
      .jobId(reviewDto.getJobId())
      .build();
  }

  public ReviewDto mapToReviewDto(Review review) {
    return ReviewDto.builder()
      .id(review.getId())
      .givenByUser(review.getGivenByUser())
      .receivedBySpecialist(review.getReceivedBySpecialist())
      .stars(review.getStars())
      .title(review.getTitle())
      .description(review.getDescription())
      .jobId(review.getJobId())
      .build();
  }
}
