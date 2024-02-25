package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.entity.Bid;

import java.util.List;

public interface BidService {
  BidDto addBid(BidDto bidDto);

  List<BidDto> findAll();

  Bid save(Bid bid);

  BidDto getById(Long id);

  int getActiveBidCount(int specialistId);


}
