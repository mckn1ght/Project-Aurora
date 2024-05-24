package com.example.cloudComputing.dto;

import com.example.cloudComputing.validator.Email;
import com.example.cloudComputing.validator.OnlyLetters;
import com.example.cloudComputing.validator.PhoneNumber;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDto {

  private Long id;

  private int rating;

}
