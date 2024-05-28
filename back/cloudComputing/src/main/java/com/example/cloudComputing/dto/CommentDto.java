package com.example.cloudComputing.dto;

import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

  private Long id;
  private Job job;
  private User user;
  private String content;
  private LocalDateTime timestamp;

}
