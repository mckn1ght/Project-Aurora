package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.dto.BidWithSpecialistInfo;
import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Bid;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.Specialist;
import com.example.cloudComputing.mapper.BidMapper;
import com.example.cloudComputing.repository.BidRepository;
import com.example.cloudComputing.repository.JobRepository;
import com.example.cloudComputing.repository.SpecialistRepository;
import com.example.cloudComputing.serviceImpl.BidService;
import com.example.cloudComputing.serviceImpl.BidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/bid")
public class BidController {
  @Autowired
  private BidServiceImpl bidServiceImpl;

  @Autowired
  BidMapper bidMapper;

  @Autowired
  private BidService bidService;

  @PostMapping("/add")
  public ResponseEntity<BidDto> addBid(@RequestBody BidDto bidDto) {
    return ResponseEntity.ok(bidServiceImpl.addBid(bidDto));
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<BidDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(bidServiceImpl.getById(id));
  }

  @RequestMapping("/getAllByJobId/{id}")
  public List<BidDto> getAllByJobId(@PathVariable int id) {
    return bidServiceImpl.getByJobId(id);
  }

  @GetMapping("/all")
  public List<BidDto> getAll() {
    return bidServiceImpl.findAll();
  }

  @GetMapping("/biddingSpecialist/{id}")
  public ResponseEntity<List<BidDto>> getAllByBiddingSpecialist(@PathVariable int id) {
    return ResponseEntity.ok(bidServiceImpl.findAllBySpecialistId(id));
  }
  @GetMapping("/activeCount/{specialistId}")
  public ResponseEntity<Integer> getActiveBidCount(@PathVariable int specialistId) {
    int activeBidCount = bidServiceImpl.getActiveBidCount(specialistId);
    return ResponseEntity.ok(activeBidCount);
  }

  @GetMapping("/getActiveBidCountByJobId/{jobId}")
  public ResponseEntity<Integer> getActiveBidCountByJobId(@PathVariable int jobId) {
    int activeBidCount = bidServiceImpl.getActiveBidCountByJobId(jobId);
    return ResponseEntity.ok(activeBidCount);
  }

  @DeleteMapping("/deleteById/{bidId}")
  public ResponseEntity<?> deleteBid(@PathVariable Long bidId) {
    try {
      bidServiceImpl.deleteById(bidId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/{bidId}/status/{status}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Object> setStatus(@PathVariable long bidId, @PathVariable String status) {
    System.out.println("Controller");
    try {
      bidServiceImpl.setBidStatus(bidId, status);
      return ResponseEntity.ok().body(Map.of("message", "Status of bid  with ID " + bidId + " updated successfully"));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }
  }

  @PutMapping("/job/{jobId}/except/{exceptBidId}")
//  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<String> acceptBid(
    @PathVariable Long jobId,
    @PathVariable Long exceptBidId
  ) {
    bidServiceImpl.acceptBid(jobId, exceptBidId);
    return ResponseEntity.status(HttpStatus.OK).body("Bids accepted successfully");
  }

  @GetMapping("/getAcceptedByJobId/{jobId}")
  public ResponseEntity<Bid> getActiveBidCountByJobId(@PathVariable Long jobId) {
    Bid bid = bidServiceImpl.getAcceptedBidByJobId(jobId);
    return ResponseEntity.ok(bid);
  }
}
