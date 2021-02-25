package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.TweetRepository;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDto;
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
}
