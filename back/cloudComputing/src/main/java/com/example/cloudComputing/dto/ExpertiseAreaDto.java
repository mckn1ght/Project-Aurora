package com.example.cloudComputing.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpertiseAreaDto {

  private Long id;

  @NotNull(message = "Expertise area name is mandatory!")
  private String name;

}
