package com.example.cloudComputing.constants;

import com.example.cloudComputing.exception.job.NoJobFoundException;

public class Constants {
  //User
  public static final String NO_USER_ID_FOUND = "No user with id: '%s' ";
  public static final String NO_USER_USERNAME_FOUND = "The username '%s' does not exist.";
  public static final String NO_USER_FOUND = "No users found.";
  public static final String NO_USER_ISADMIN_FOUND = "No users with isAdmin value '%s' found";
  public static final String NO_USER_WITH_CREDIT_HIGHER_THAN = "No user with credit higher than '%s' found.";
  public static final String NO_USER_WITH_CREDIT_LOWER_THAN = "No user with credit lower than '%s' found.";
  public static final String NO_USER_SPECIALIST_ID_FOUND = "No user with specialist id '%s' found.";

  //Specialist
  public static final String NO_SPECIALIST_ID_FOUND = "No specialist with id '%s' found.";
  public static final String NO_SPECIALIST_FOUND = "No specialist found.";
  public static final String NO_SPECIALIST_EXPERTISE_AREA_FOUND = "No specialist for '%s' found.";
  public static final String NO_SPECIALIST_COVERAGE_AREA_FOUND = "No specialist in '%s' found.";

  //Review

  public static final String NO_REVIEW_FOUND = "No review was found";
  public static final String NO_REVIEW_GIVEN_BY_ID = "No review was given the the user id '%s' ";
  public static final String NO_REVIEW_ID_FOUND = "No review with id '%s' found.";
  public static final String NO_REVIEW_RECEIVED_BY_ID = "No review was received by the specialist with id '%s'";
  public static final String NO_REVIEW_WITH_STARS = "No review with '%s' stars found.";
  public static final String NO_REVIEW_WITH_STARS_BEFORE = "No review with lesser stars than '%s' was found.";
  public static final String NO_REVIEW_WITH_STARS_AFTER = "No review with greater stars than '%s' was found.";

  //ExpertiseArea
  public static final String NO_EXPAREA_FOUND = "No expertise area found";
  public static final String NO_EXPAREA_ID_FOUND = "No expertise area with id '%s' found.";
  public static final String NO_EXPAREA_NAME_FOUND = "No expertise area '%s' found.";

  //Job
  public static final String NO_JOB_FOUND = "No job found.";
  public static final String NO_JOB_ID_FOUND = "No job with id '%s' found.";
  public static final String NO_JOB_WITH_TITLE_CONTAINING = "No job with title '%s'.";
  public static final String NO_JOB_WITH_DESCRIPTION_CONTAINING = "No job with description '%s'.";
  public static final String NO_JOB_WITH_LOCATION_CONTAINING = "No job in '%s' found.";
  public static final String NO_JOBS_CREATED_BY_USER = "No jobs created by user '%s'.";
  public static final String NO_JOBS_ASSIGNED_TO_SPECIALIST = "No jobs assigned to specialist '%s'.";
  public static final String NO_JOBS_CREATED_ON_DATE = "No jobs created on '%s'.";
  public static final String NO_JOBS_CREATED_BEFORE_DATE = "No jobs created before '%s'.";
  public static final String NO_JOBS_CREATED_AFTER_DATE = "No jobs created after '%s'.";
  public static final String NO_JOBS_WITH_DUE_DATE = "No jobs with due date on '%s'.";
  public static final String NO_JOBS_AFTER_DUE_DATE = "No jobs with due date after '%s'.";
  public static final String NO_JOBS_BEFORE_DUE_DATE = "No jobs with due date before '%s'.";
  public static final String NO_JOBS_LAST_UPDATED_ON_DATE = "No jobs last updated '%s'.";
  public static final String NO_JOBS_LAST_UPDATED_BEFORE_DATE = "No jobs last updated before '%s'.";
  public static final String NO_JOBS_LAST_UPDATED_AFTER_DATE = "No jobs last updated after '%s'.";
  public static final String NO_JOBS_ON_WORKFLOW_STEP = "No jobs on workflow step '%s'.";

  //Bid
  public static final String NO_BID_FOUND = "No bids found.";
  public static final String NO_BID_ID_FOUND = "No bids with id '%s' found.";

  //Resource
  public static final String NO_RESOURCE_FOUND = "Resource not found.";
}
