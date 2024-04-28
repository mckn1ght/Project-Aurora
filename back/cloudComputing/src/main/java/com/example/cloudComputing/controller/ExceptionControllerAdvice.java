package com.example.cloudComputing.controller;

import com.example.cloudComputing.exception.bid.NoBidFoundException;
import com.example.cloudComputing.exception.bid.NoBidIdFoundException;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaFoundException;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaIdFoundException;
import com.example.cloudComputing.exception.expertiseArea.NoExpertiseAreaNameFoundException;
import com.example.cloudComputing.exception.job.*;
import com.example.cloudComputing.exception.resource.NoResourceFoundException;
import com.example.cloudComputing.exception.review.*;
import com.example.cloudComputing.exception.specialist.NoSpecialistCoverageAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistExpertiseAreaFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistFoundException;
import com.example.cloudComputing.exception.specialist.NoSpecialistIdFoundException;
import com.example.cloudComputing.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionControllerAdvice {
  @ExceptionHandler({NoCreditHigherFoundException.class, NoCreditLowerFoundException.class, NoUserFoundException.class,
    NoUserIsAdminFoundException.class, UserIdNotFoundException.class, UserUsernameNotFoundException.class,
    SQLIntegrityConstraintViolationException.class, NoSpecialistFoundException.class, NoSpecialistIdFoundException.class,
    NoSpecialistCoverageAreaFoundException.class, NoSpecialistExpertiseAreaFoundException.class, NoReviewFoundException.class,
    NoReviewGivenByIdException.class, NoReviewIdFoundException.class, NoReviewsReceivedBySpecialistIdException.class,
    NoReviewsWithStarsAfterFoundException.class, NoReviewsWithStarsFoundException.class, NoReviewsWithStarsSmallerFoundException.class,
    NoExpertiseAreaFoundException.class, NoExpertiseAreaIdFoundException.class, NoExpertiseAreaNameFoundException.class,
    JobIdNotFoundException.class, NoJobFoundException.class, NoJobWithTitleContainingException.class,
    NoJobWithDescriptionContainingException.class, NoJobWithLocationContainingException.class, NoJobCreatedByUserException.class,
    NoJobsAssignedToSpecialistException.class, NoJobsCreatedOnDateException.class, NoJobsCreatedBeforeDateException.class,
    NoJobsCreatedAfterDateException.class, NoJobsWithDueDateException.class, NoJobsAfterDueDateException.class,
    NoJobsBeforeDueDateException.class, NoJobsLastUpdatedOnDateException.class, NoJobsLastUpdatedBeforeDateException.class,
    NoJobsLastUpdatedAfterDateException.class, NoJobsOnWorkflowStepException.class, NoBidFoundException.class,
    NoBidIdFoundException.class, NoResourceFoundException.class
  })

  public ResponseEntity<String> handleControllerExceptions(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest()
      .body(e.getFieldError().getDefaultMessage());

  }
}






