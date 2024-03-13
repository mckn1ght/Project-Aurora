package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Job;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface JobService {
  JobDto add(JobDto jobDto);

  List<JobDto> getAll();

  JobDto getById(Long id);

  List<JobDto> getAllByTitleContainingIgnoreCase(String location);

  List<JobDto> getAllByDescriptionContainingIgnoreCase(String location);

  List<JobDto> getAllByLocationContainingIgnoreCase(String location);

  List<JobDto> getAllByCreatedByUser(int id);

  List<JobDto> getAllByAssignedToSpecialist(int id);

  List<JobDto> getAllByCreationDate(LocalDate date);

  List<JobDto> getAllByCreationDateBefore(LocalDate date);

  List<JobDto> getAllByCreationDateAfter(LocalDate date);

  List<JobDto> getAllByDueDate(LocalDate date);

  List<JobDto> getAllByDueDateAfter(LocalDate date);

  List<JobDto> getAllByDueDateBefore(LocalDate date);

  List<JobDto> getAllByLastUpdated(LocalDate date);

  List<JobDto> getAllByLastUpdatedBefore(LocalDate date);

  List<JobDto> getAllByLastUpdatedAfter(LocalDate date);

  List<JobDto> getAllByCurrentWorkflowStep(String step);
  void assignJobToSpecialist(long jobId, int specialistId);



}
