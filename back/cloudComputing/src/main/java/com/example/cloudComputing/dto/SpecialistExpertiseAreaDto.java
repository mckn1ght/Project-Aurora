package com.example.cloudComputing.dto;

import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistExpertiseAreaDto {

  private Long id;

  private int specialistId;

  private int expertiseAreaId;
}
