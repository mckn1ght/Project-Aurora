package com.example.cloudComputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "specialist")
public class Specialist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "rating", nullable = false, columnDefinition = "int default 5")
  private int rating = 5;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "specialist_expertiseArea",
    joinColumns = @JoinColumn(name = "specialistID"),
    inverseJoinColumns = @JoinColumn(name = "expertiseAreaID")
  )
  private Set<ExpertiseArea> expertiseAreas;
}




