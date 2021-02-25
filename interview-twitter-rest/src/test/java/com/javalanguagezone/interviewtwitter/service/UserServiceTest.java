package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.TweetRepository;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDto;
import com.javalanguagezone.interviewtwitter.service.dto.UserRegistrationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Test(expected = UsernameNotFoundException.class)
  public void loadingUserWithUnknownUsername_UsernameNotFoundExceptionThrown() {
    userService.loadUserByUsername("unknownUser");
  }

  @Test(expected = UsernameNotFoundException.class)
  public void testLoadingUser_ExpectExceptionThrown(){
    userService.getUserOverview("andreasAntonopoulos");
  }

  @Test
  public void testLoadingUser_ExpectFullUserOverview(){
    UserOverviewDto userOverview = userService.getUserOverview("aantonop");
    assertNotNull(userOverview);
    assertEquals(userOverview.getFollowingCount(), 4);
    assertEquals(userOverview.getFollowerCount(), 1);
    assertEquals(userOverview.getTweetCount(), 3);
  }

  @Test
  public void registerUser_ExpectUserIsCreated(){
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    userRegistrationDto.setFullName("Lana Del Rey");
    userRegistrationDto.setPassword("LanaOdKralja");
    userRegistrationDto.setUsername("ldr");
    userService.userRegistration(userRegistrationDto);

    User savedInDbUser = userRepository.findOneByUsername("ldr");
    assertNotNull(savedInDbUser);
    assertEquals(savedInDbUser.getUsername(), userRegistrationDto.getUsername());
    assertEquals(savedInDbUser.getFullName(), userRegistrationDto.getFullName());
    assertEquals(savedInDbUser.getPassword(), userRegistrationDto.getPassword());
    //assertEquals(savedInDbUser.getFollowing().size(), 0); Needs manytomany fetch type eager on variable
    //assertEquals(savedInDbUser.getFollowers().size(), 0);

  }

  @Test(expected = UserService.UserAlreadyIsRegisteredException.class)
  public void registeringUserThatExists_ExpectAlreadyRegisterExceptionThrown() {
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    userRegistrationDto.setUsername("satoshiNakamoto");
    userRegistrationDto.setPassword("testiiiiiiii");
    
    userService.userRegistration(userRegistrationDto);
  }
}
