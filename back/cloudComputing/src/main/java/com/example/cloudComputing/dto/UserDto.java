package com.example.cloudComputing.dto;

import com.example.cloudComputing.validator.Email;
import com.example.cloudComputing.validator.Password;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;


  @NotNull(message = "Username is mandatory!")
  private String username;
//  @Password() commented because the password is hashed in frontend
  private String password;
  private String address;
  @Email()
  private String email;
  private Date created;
  private Date last_updated;
  private double credit;
  private int permissions;

}
