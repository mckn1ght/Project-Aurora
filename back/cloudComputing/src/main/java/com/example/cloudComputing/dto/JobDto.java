package com.example.cloudComputing.dto;

import com.example.cloudComputing.validator.Email;
import com.example.cloudComputing.validator.OnlyLetters;
import com.example.cloudComputing.validator.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

  private Long id;
  @NotNull(message = "Title is mandatory!")
  private String title;
  @NotNull(message = "Description is mandatory!")
  private String description;
  @NotNull(message = "Location is mandatory!")
  private String location;
  @NotNull
  private int createdByUser;
  private int assignedToSpecialist;
  @NotNull
  private LocalDate creationDate;
  @NotNull
  private LocalDate dueDate;
  @NotNull
  private LocalDate lastUpdated;
  @NotNull
  private String currentWorkflowStep;

}
