package com.example.cloudComputing.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {

  private Long id;

  private int userId;

  private String type;

  private String location = "../../assets/users/";

  private Date createdAt;

  private Date updatedAt;
}
