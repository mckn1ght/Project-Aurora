package com.example.cloudComputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "location")
  private String location;

  @Column(name = "createdByUser")
  private int createdByUser;

  @Column(name = "assignedToSpecialist")
  private int assignedToSpecialist = 0;
//  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creationDate")
  private LocalDate creationDate;

//  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "dueDate")
  private LocalDate dueDate;
//  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "lastUpdated")
  private LocalDate lastUpdated;

  @Column(name = "currentWorkflowStep")
  private String currentWorkflowStep;

  // Created Date will be set before the entity is first saved to the database
  @PrePersist
  protected void onCreate() {
    creationDate = LocalDate.now();
    lastUpdated = creationDate;
  }

  // Last Updated will be set before every update
  @PreUpdate
  protected void onUpdate() {
    lastUpdated = LocalDate.now();
  }

}
