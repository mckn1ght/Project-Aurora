package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.BidDto;
import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.mapper.BidMapper;
import com.example.cloudComputing.serviceImpl.BidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bid")
public class BidController {
  @Autowired
  private BidServiceImpl bidServiceImpl;

  @Autowired
  BidMapper bidMapper;

  @PostMapping("/add")
  public ResponseEntity<BidDto> addBid(@RequestBody BidDto bidDto) {
    return ResponseEntity.ok(bidServiceImpl.addBid(bidDto));
  }

  @RequestMapping("/getById/{id}")
  public ResponseEntity<BidDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(bidServiceImpl.getById(id));
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

  @DeleteMapping("/deleteById/{bidId}")
  public ResponseEntity<?> deleteBid(@PathVariable Long bidId) {
    try {
      bidServiceImpl.deleteById(bidId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
