package com.example.cloudComputing.dto;

import com.example.cloudComputing.validator.Email;
import com.example.cloudComputing.validator.OnlyLetters;
import com.example.cloudComputing.validator.PhoneNumber;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
  @NotEmpty(message = "First name can not be empty")
  @NotNull(message = "First name can not be empty")
  @OnlyLetters(message = "The first name must contain only letters.")
  private String firstName;
  @NotEmpty(message = "Last name can not be empty")
  @NotNull(message = "Last name can not be empty")
  @OnlyLetters(message = "The last name must contain only letters.")
  private String lastName;
  private String address;
  @Email()
  private String email;
  @PhoneNumber()
//  @NotEmpty(message = "Phone number can not be empty")
//  @NotNull(message = "Phone number can not be empty")
  private String phoneNumber;
  private Date created;
  private Date last_updated;
  private double credit;
  private boolean isAdmin;
  private Integer specialistId;

}
