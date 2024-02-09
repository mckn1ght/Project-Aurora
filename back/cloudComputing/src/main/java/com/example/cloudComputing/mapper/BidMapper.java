package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.entity.Bid;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BidMapper {
  public Bid mapToBid(BidDto bidDto) {
    return Bid.builder()
      .id(bidDto.getId())
      .jobId(bidDto.getJobId())
      .specialistId(bidDto.getSpecialistId())
      .amount(bidDto.getAmount())
      .status(bidDto.getStatus())
      .createdAt(bidDto.getCreatedAt())
      .updatedAt(bidDto.getUpdatedAt())
      .build();
  }

  public BidDto mapToBidDto(Bid bid) {
    return BidDto.builder()
      .id(bid.getId())
      .jobId(bid.getJobId())
      .specialistId(bid.getSpecialistId())
      .amount(bid.getAmount())
      .status(bid.getStatus())
      .createdAt(bid.getCreatedAt())
      .updatedAt(bid.getUpdatedAt())
      .build();
  }
}
