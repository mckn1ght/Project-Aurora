package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.ReviewDto;
import com.example.cloudComputing.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  ReviewDto save(ReviewDto reviewDto);

  List<Review> findAll();

  @Override
  Review getReferenceById(Long aLong);

  List<Review> getAllByGivenByUser(int id);

  @Query("SELECT r FROM Review r WHERE r.receivedBySpecialist = ?1")
  List<Review> getAllByReceivedBySpecialist(int id);

  List<Review> getAllByStars(int stars);

  List<Review> getAllByStarsBefore(int stars);

  List<Review> getAllByStarsAfter(int stars);

  int countAllByReceivedBySpecialist(Long specialistId);


}
