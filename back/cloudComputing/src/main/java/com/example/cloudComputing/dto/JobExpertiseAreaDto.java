package com.example.cloudComputing.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobExpertiseAreaDto {

  private Long id;

  private int jobId;

  private int expertiseAreaId;
}
