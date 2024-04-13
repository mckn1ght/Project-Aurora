package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
  Bid save(Bid bid);

  List<Bid> findAll();

  Bid getById(Long id);

  List<Bid> findAllBySpecialistId(int id);

  @Query("SELECT COUNT(b) FROM Bid b WHERE b.specialistId = :specialistId AND b.status = 'active'")
  int countActiveBidsBySpecialistId(@Param("specialistId") int specialistId);

  @Query("SELECT COUNT(b) FROM Bid b WHERE b.jobId = :jobId AND b.status = 'active'")
  int countActiveBidsByJobId(@Param("jobId") int jobId);

  @Query("SELECT b FROM Bid b WHERE b.jobId = :jobId AND b.status = 'active'")
  List<Bid> getAllByJobId(int jobId);

}
