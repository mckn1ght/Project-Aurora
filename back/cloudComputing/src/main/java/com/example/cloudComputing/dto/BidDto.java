package com.example.cloudComputing.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BidDto {

  private Long id;
  private int jobId;
  private int specialistId;
  private double amount;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
