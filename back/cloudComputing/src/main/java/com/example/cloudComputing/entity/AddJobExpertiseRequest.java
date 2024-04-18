package com.example.cloudComputing.entity;

import lombok.Data;

import java.util.List;

@Data
public class AddJobExpertiseRequest {

  private Long jobId;
  private List<Integer> expertiseAreas;
}
