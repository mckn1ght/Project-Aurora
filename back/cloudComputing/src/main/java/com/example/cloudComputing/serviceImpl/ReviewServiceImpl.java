package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Review;
import com.example.cloudComputing.exception.review.*;
import com.example.cloudComputing.mapper.ReviewMapper;
import com.example.cloudComputing.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ReviewMapper reviewMapper;

  public ReviewDto add(ReviewDto reviewDto) {
    return reviewMapper.mapToReviewDto(reviewRepository.save(reviewMapper.mapToReview(reviewDto)));
  }

  public List<ReviewDto> getAll() {
    List<ReviewDto> reviewDtos = reviewRepository.findAll().stream().map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if (reviewDtos.isEmpty()) {
      throw new NoReviewFoundException(NO_REVIEW_FOUND);
    }
    return reviewDtos;
  }

  public ReviewDto getReferenceById(Long id) {
    try {
      Review review = reviewRepository.getReferenceById(id);
      return reviewMapper.mapToReviewDto(review);
    }catch(Exception e){
      throw new NoReviewIdFoundException(String.format(NO_REVIEW_ID_FOUND, id));
    }
  }

  public List<ReviewDto> getAllByGivenByUser(int id) {
    List<ReviewDto> reviewDtos = reviewRepository.getAllByGivenByUser(id).stream()
      .map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if (reviewDtos.isEmpty()) {
      throw new NoReviewGivenByIdException(String.format(NO_REVIEW_GIVEN_BY_ID, id));
    }
    return reviewDtos;
  }

  public List<ReviewDto> getAllByReceivedBySpecialist(int id){
    List<ReviewDto> reviewDtos = reviewRepository.getAllByReceivedBySpecialist(id).stream()
      .map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if(reviewDtos.isEmpty()){
      throw new NoReviewsReceivedBySpecialistIdException(String.format(NO_REVIEW_RECEIVED_BY_ID, id));
    }
    return reviewDtos;
  }

  public List<ReviewDto> getAllByStars(int stars){
    List<ReviewDto> reviewDtos = reviewRepository.getAllByStars(stars).stream()
      .map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if(reviewDtos.isEmpty()){
      throw new NoReviewsWithStarsFoundException(String.format(NO_REVIEW_WITH_STARS, stars));
    }
    return reviewDtos;
  }

  public List<ReviewDto> getAllByStarsBefore(int stars){
    List<ReviewDto> reviewDtos = reviewRepository.getAllByStarsBefore(stars).stream()
      .map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if(reviewDtos.isEmpty()){
      throw new NoReviewsWithStarsSmallerFoundException(String.format(NO_REVIEW_WITH_STARS_BEFORE, stars));
    }
    return reviewDtos;
  }

  public List<ReviewDto> getAllByStarsAfter(int stars){
    List<ReviewDto> reviewDtos = reviewRepository.getAllByStarsAfter(stars).stream()
      .map(review -> reviewMapper.mapToReviewDto(review)).collect(Collectors.toList());
    if(reviewDtos.isEmpty()){
      throw new NoReviewsWithStarsAfterFoundException(String.format(NO_REVIEW_WITH_STARS_AFTER, stars));
    }
    return reviewDtos;
  }

  public Double getAverageRating(int specialistId) {
    List<Review> reviews = reviewRepository.getAllByReceivedBySpecialist(specialistId);
    System.out.println("Reviews for Specialist ID " + specialistId + ": " + reviews);
    if (reviews.isEmpty()) {
      return 5.0; // Default rating when no reviews
    }

    double sum = 0.0;
    for (Review review : reviews) {
      sum += review.getStars();
      System.out.println("Processing review: " + review);
    }

    double average = sum / reviews.size();
    System.out.println("Calculated average: " + average);
    return average;
  }

  public int countAllByReceivedSpecialist(Long specialistId) {
   int cnt = reviewRepository.countAllByReceivedBySpecialist(specialistId);
   return cnt;
  }

}



