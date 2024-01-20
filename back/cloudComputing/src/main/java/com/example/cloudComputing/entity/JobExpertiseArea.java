package com.example.cloudComputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_experiseArea")
public class JobExpertiseArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long jobid;

  @Column(name = "expertiseAreaId")
  private int expertiseAreaId;

}
