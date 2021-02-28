package com.javalanguagezone.interviewtwitter.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserRegistrationDto {

  private String username;

  private String password;

  private String fullName;

}
