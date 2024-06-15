package com.example.cloudComputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "givenByUser")
  private int givenByUser;

  @Column(name = "receivedBySpecialist")
  private int receivedBySpecialist;

  @Column(name = "stars")
  private int stars;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "jobId")
  private int jobId;

}


