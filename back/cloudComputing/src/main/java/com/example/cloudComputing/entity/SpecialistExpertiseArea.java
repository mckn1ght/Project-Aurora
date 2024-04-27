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
@Table(name = "specialist_expertiseArea")
public class SpecialistExpertiseArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "specialistId")
  private int specialistId;

  @Column(name = "expertiseAreaId")
  private int expertiseAreaId;

}
