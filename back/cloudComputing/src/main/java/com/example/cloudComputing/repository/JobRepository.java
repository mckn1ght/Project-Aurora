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

  @Query("SELECT j FROM Job j WHERE j.assignedToSpecialist = :userId AND j.currentWorkflowStep IN ('Preluat', 'Waiting for validation')")
  List<Job> getAllByAssignedToSpecialist(int userId);

  List<Job> getAllByCreationDate(LocalDate date);

  List<Job> getAllByCreationDateBefore(LocalDate date);

  List<Job> getAllByCreationDateAfter(LocalDate date);

  List<Job> getAllByDueDate(LocalDate date);

  List<Job> getAllByDueDateAfter(LocalDate date);

  List<Job> getAllByDueDateBefore(LocalDate date);

  List<Job> getAllByLastUpdated(LocalDate date);

  List<Job> getAllByLastUpdatedBefore(LocalDate date);

  List<Job> getAllByLastUpdatedAfter(LocalDate date);

  @Query("SELECT j FROM Job j WHERE j.currentWorkflowStep IN ('New', 'In Licitatie')")
  List<Job> getAllByCurrentWorkflowStep();

  @Query(value = "SELECT DISTINCT j.*, b.* FROM job j INNER JOIN bid b ON j.id = b.jobID WHERE j.createdByUser = :userId AND " +
    "j.currentWorkflowStep NOT IN ('Preluat', 'In executie', 'New', 'Waiting for Validation') AND b.status IN ('active') ", nativeQuery = true)
  List<Object[]> findJobsAndBidsCreatedByUser(Long userId);

  @Query("SELECT j FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('Preluat', 'In executie', 'Waiting for Validation')")
  List<Job> findJobsCreatedByUserWithStatus(@Param("userId") Long userId);

  @Query("SELECT j FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('New')")
  List<Job> findNewJobsCreatedByUser(@Param("userId") Long userId);

  @Query("SELECT COUNT(j) FROM Job j WHERE j.createdByUser = :userId")
  int countJobsCreatedByUser(@Param("userId") Long userId);

  @Query("SELECT COUNT(j) FROM Job j WHERE j.assignedToSpecialist = :specialistId AND j.currentWorkflowStep IN ('Validated')")
  int countJobsFinishedBySpecialist(@Param("specialistId") Long specialistId);
  @Query("SELECT COUNT(j) FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('Validated')")
  int countJobsFinishedByUser(@Param("userId") Long userId);

  @Query("SELECT COUNT(j) FROM Job j WHERE j.createdByUser = :userId AND j.currentWorkflowStep IN ('Canceled')")
  int countJobsCanceledByUser(@Param("userId") Long userId);

  @Query("SELECT COUNT(j) FROM Job j WHERE j.assignedToSpecialist = :specialistId AND j.currentWorkflowStep IN ('Preluat', 'Waiting for validation')")
  int countActiveJobsInWorkBySpecialist(@Param("specialistId") Long specialistId);

  List<Job> findByExpertiseAreasId(int expertiseAreaId);
}



