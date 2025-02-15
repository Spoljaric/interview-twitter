package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.controller.dto.ErrorMessage;
import com.javalanguagezone.interviewtwitter.service.TweetService;
import com.javalanguagezone.interviewtwitter.service.UserService;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDto;
import com.javalanguagezone.interviewtwitter.service.dto.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@Slf4j
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/followers")
  public Collection<UserDTO> followers(Principal principal) {
    return userService.getUsersFollowers(principal);
  }

  @GetMapping("/following")
  public Collection<UserDTO> following(Principal principal) {
    return userService.getUsersFollowing(principal);
  }

  @GetMapping("/overview/{username}")
  public UserOverviewDto userOverview(@PathVariable String username) {
    log.info("Getting user overview for user {}", username);
    return userService.getUserOverview(username);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody UserRegistrationDto userRegistration) {
    log.info(userRegistration.toString());
    userService.userRegistration(userRegistration);
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorMessage handleInvalidUserNameException(UserService.InvalidUserNameException e) {
    log.warn("Bad request received: {}", e);
    return new ErrorMessage(String.format("Invalid username '%s'", e.getUsername()));
  }

  @ExceptionHandler
  @ResponseStatus(CONFLICT)
  public ErrorMessage handleUsernameExistsException(UserService.UserAlreadyIsRegisteredException e) {
    log.warn("Conflict detected: {}", e);
    return new ErrorMessage(String.format("Username already exists '%s'", e.getUsername()));
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorMessage InvalidUserRegistrationException(UserService.InvalidUserRegistrationException e) {
    log.warn("Invalid registration: {}", e);
    return new ErrorMessage(String.format("Invalid details for registration"));
  }

}
