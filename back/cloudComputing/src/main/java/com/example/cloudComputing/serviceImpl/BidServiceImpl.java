package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.*;
import com.example.cloudComputing.entity.Bid;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.exception.bid.NoBidIdFoundException;
import com.example.cloudComputing.exception.job.JobIdNotFoundException;
import com.example.cloudComputing.exception.job.NoJobsOnWorkflowStepException;
import com.example.cloudComputing.mapper.BidMapper;
import com.example.cloudComputing.mapper.JobMapper;
import com.example.cloudComputing.mapper.SpecialistMapper;
import com.example.cloudComputing.mapper.UserMapper;
import com.example.cloudComputing.repository.BidRepository;
import com.example.cloudComputing.repository.JobRepository;
import com.example.cloudComputing.repository.SpecialistRepository;
import com.example.cloudComputing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import com.example.cloudComputing.dto.JobDto;


@Service
public class BidServiceImpl implements BidService {

  @Autowired
  private BidRepository bidRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BidMapper bidMapper;

  @Autowired
  private JobMapper jobMapper;
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private SpecialistMapper specialistMapper;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private SpecialistRepository specialistRepository;
  public JobDto getJobInfo(Long jobId) {
    Job job = jobRepository.findById(jobId).orElseThrow(() -> new NoSuchElementException("Job not found"));
    return jobMapper.mapToJobDto(job);
  }

  public List<BidDto> getBidsForJob(int jobId) {
    List<Bid> bids = bidRepository.getAllByJobId(jobId);
    return bidMapper.mapToBidDtoList(bids);
  }

//  public List<SpecialistDto> getSpecialistsForJob(int jobId) {
//    List<Specialist> specialists = specialistRepository.findDistinctByBids_JobId(jobId);
//    return specialistMapper.mapToSpecialistDtoList(specialists);
//  }

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

  public int getActiveBidCountByJobId(int specialistId) {
    // Implement logic to count active bids for the given user
    return bidRepository.countActiveBidsByJobId(specialistId);
  }

  public void deleteById(Long bidId) {
    bidRepository.deleteById(bidId);
  }

  @Override
  public List<BidDto> getBidsByJobId(int jobId) {
    List<Bid> bids = bidRepository.getAllByJobId(jobId);
    return bids.stream()
      .map(bidMapper::mapToBidDto)
      .collect(Collectors.toList());
  }

  public void setBidStatus(long bidId, String status) {
    System.out.println("Service MEthod");
    Bid bid = bidRepository.getById(bidId);
    if (bid != null) {
      bid.setStatus(status);
      bidRepository.save(bid);
    } else {
      throw new RuntimeException(NO_BID_ID_FOUND + bidId);
    }
  }


  public void acceptBid(Long jobId, Long exceptBidId) {
    bidRepository.acceptBid(jobId, exceptBidId);
  }

}
