package com.example.cloudComputing.dto;

import com.example.cloudComputing.entity.Bid;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor


public class BidWithSpecialistInfo {
  private JobDto job;
  private List<BidDto> bids;
  private List<SpecialistDto> specialists;
  private List<UserDto> users;

  public BidWithSpecialistInfo(JobDto job, List<BidDto> bids, List<SpecialistDto> specialists, List<UserDto> users) {
    this.job = job;
    this.bids = bids;
    this.specialists = specialists;
    this.users = users;
  }

  // Getters and setters
}

