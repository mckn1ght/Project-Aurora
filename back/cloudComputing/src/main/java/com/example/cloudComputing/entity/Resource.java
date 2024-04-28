package com.example.cloudComputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resource")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "userId")
  private int userId;

  @Column(name = "type")
  private String type;

  @Column(name = "location")
  private String location = "../../assets/users/";

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createdAt")
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updatedAt")
  private Date updatedAt;

  // Created Date will be set before the entity is first saved to the database
  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
    updatedAt = createdAt;
  }

  // Last Updated will be set before every update
  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }

}
