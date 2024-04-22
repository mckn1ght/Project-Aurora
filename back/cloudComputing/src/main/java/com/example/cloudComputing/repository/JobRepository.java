package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
  JobDto save(JobDto jobDto);

  List<Job> findAll();

  Job getById(Long id);

  List<Job> getAllByTitleContainingIgnoreCase(String title);

  List<Job> getAllByDescriptionContainingIgnoreCase(String description);

  List<Job> getAllByLocationContainingIgnoreCase(String location);

  List<Job> getAllByCreatedByUser(int id);

  List<Job> getAllByAssignedToSpecialist(int id);

  List<Job> getAllByCreationDate(LocalDate date);

  List<Job> getAllByCreationDateBefore(LocalDate date);

  List<Job> getAllByCreationDateAfter(LocalDate date);

  List<Job> getAllByDueDate(LocalDate date);

  List<Job> getAllByDueDateAfter(LocalDate date);

  List<Job> getAllByDueDateBefore(LocalDate date);

  List<Job> getAllByLastUpdated(LocalDate date);

  List<Job> getAllByLastUpdatedBefore(LocalDate date);

  List<Job> getAllByLastUpdatedAfter(LocalDate date);

  List<Job> getAllByCurrentWorkflowStep(String step);

  @Query(value = "SELECT DISTINCT j.*, b.* FROM job j INNER JOIN bid b ON j.id = b.jobID WHERE j.createdByUser = :userId AND " +
    "j.currentWorkflowStep NOT IN ('Preluat', 'In executie', 'New') AND b.status IN ('active')", nativeQuery = true)
  List<Object[]> findJobsAndBidsCreatedByUser(Long userId);

  @Query("SELECT j FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('Preluat', 'In executie')")
  List<Job> findJobsCreatedByUserWithStatus(@Param("userId") Long userId);

  @Query("SELECT j FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('New')")
  List<Job> findNewJobsCreatedByUser(@Param("userId") Long userId);

  @Query("SELECT COUNT(j) FROM Job j WHERE j.createdByUser = :userId")
  int countJobsCreatedByUser(@Param("userId") Long userId);

  List<Job> findByExpertiseAreasId(int expertiseAreaId);
}



