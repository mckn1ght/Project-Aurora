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
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "address")
  private String address;

  @Column(name = "email")
  private String email;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created")
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_updated")
  private Date last_updated;

  @Column(name = "credit")
  private double credit;

  @Column(name = "permissions")
  private int permissions;

  // Created Date will be set before the entity is first saved to the database
  @PrePersist
  protected void onCreate() {
    created = new Date();
    last_updated = created;
  }

  // Last Updated will be set before every update
  @PreUpdate
  protected void onUpdate() {
    last_updated = new Date();
  }

}
