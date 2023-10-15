package com.example.cloudComputing.dto;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String username;
  private String password;
  private String address;
  private String email;
  private Date created;
  private Date last_updated;
  private double credit;
  private String permissions;

}
