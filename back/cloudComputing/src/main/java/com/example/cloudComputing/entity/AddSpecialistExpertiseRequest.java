package com.example.cloudComputing.entity;

import lombok.Data;

import java.util.List;

@Data
public class AddSpecialistExpertiseRequest {

  private Long specialistId;
  private List<Integer> expertiseAreas;
}
