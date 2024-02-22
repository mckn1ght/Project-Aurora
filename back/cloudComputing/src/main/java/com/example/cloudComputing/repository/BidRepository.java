package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
  Bid save(Bid bid);

  List<Bid> findAll();

  Bid getById(Long id);

  List<Bid> findAllBySpecialistId(int id);
}
