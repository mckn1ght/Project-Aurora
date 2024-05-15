package com.example.cloudComputing.dto;

import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobPictureDto {

  private Long id;

  private int jobId;

  private String url;
}
