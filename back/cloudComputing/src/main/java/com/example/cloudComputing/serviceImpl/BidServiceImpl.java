package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Bid;
import com.example.cloudComputing.exception.bid.NoBidIdFoundException;
import com.example.cloudComputing.exception.job.NoJobsOnWorkflowStepException;
import com.example.cloudComputing.mapper.BidMapper;
import com.example.cloudComputing.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BidServiceImpl implements BidService {

  @Autowired
  private BidRepository bidRepository;

  @Autowired
  private BidMapper bidMapper;

  public BidDto addBid(BidDto bidDto) {
    return bidMapper.mapToBidDto(bidRepository.save(bidMapper.mapToBid(bidDto)));
  }

  public List<BidDto> findAll() {
    List<BidDto> bidDtos = bidRepository.findAll().stream().map(bid -> bidMapper.mapToBidDto(bid)).collect(Collectors.toList());
    if (bidDtos.isEmpty()) {
      throw new NoBidIdFoundException(NO_BID_FOUND);
    }
    return bidDtos;
  }

  public BidDto getById(Long Id) {
    Bid bid = bidRepository.getById(Id);
    if (bid == null) {
      throw new NoBidIdFoundException(String.format(NO_BID_ID_FOUND, id));
    }
    return bidMapper.mapToBidDto(bid);
  }

  public List<BidDto> getByJobId(int id) {
    List<BidDto> bidDto = bidRepository.getAllByJobId(id).stream().map(bid -> bidMapper.mapToBidDto(bid))
      .collect(Collectors.toList());
    if (bidDto.isEmpty()) {
      throw new NoBidIdFoundException(String.format(NO_BID_FOUND));
    }
    return bidDto;
  }

  @Override
  public Bid save(Bid bid) {
    return bidRepository.save(bid);
  }

  public List<BidDto> findAllBySpecialistId(int id) {
    List<BidDto> bidDtos = bidRepository.findAllBySpecialistId(id).stream().map(bid -> bidMapper.mapToBidDto(bid)).collect(Collectors.toList());
    if (bidDtos.isEmpty()) {
      throw new NoBidIdFoundException(NO_BID_FOUND);
    }
    return bidDtos;
  }

  public int getActiveBidCount(int specialistId) {
    // Implement logic to count active bids for the given user
    return bidRepository.countActiveBidsBySpecialistId(specialistId);
  }

  public void deleteById(Long bidId) {
    bidRepository.deleteById(bidId);
  }
}
