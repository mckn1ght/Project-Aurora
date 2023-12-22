package com.example.cloudComputing.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

  private Long id;

  @Column(name = "givenByUser")
  @NotNull(message = "The review must have a creator user.")
  private int givenByUser;

  @Column(name = "receivedBySpecialist")
  @NotNull(message = "The review must have a receiving specialist.")
  private int receivedBySpecialist;

  @Column(name = "stars")
  @NotNull(message = "The review must have a number of stars.")
  private int stars;

  @Column(name = "title")
  @NotNull(message = "The review must have a title.")
  private String title;

  @Column(name = "description")
  @NotNull(message = "The review must have a description.")
  private String description;
}
