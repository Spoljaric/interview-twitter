package com.javalanguagezone.interviewtwitter.controller;

import com.javalanguagezone.interviewtwitter.controller.dto.ErrorMessage;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserControllerIntegrationTest extends RestIntegrationTest {

  @Test
  public void followersRequested_allFollowersReturned() {
    ResponseEntity<UserDTO[]> response = withAuthTestRestTemplate().getForEntity("/followers", UserDTO[].class);
    assertThat(response.getStatusCode().is2xxSuccessful(), is(true));
    List<UserDTO> followers = Arrays.asList(response.getBody());
    assertThat(followers, hasSize(1));
    assertThat(extractUsernames(followers), contains("rogerkver"));
  }

  @Test
  public void getFollowingFromFirstPage() {
    ResponseEntity<UserDTO[]> response = withAuthTestRestTemplate().getForEntity("/following", UserDTO[].class);
    assertThat(response.getStatusCode().is2xxSuccessful(), is(true));
    List<UserDTO> following = Arrays.asList(response.getBody());
    assertThat(following, hasSize(4));
    assertThat(extractUsernames(following), containsInAnyOrder(followingUsers()));
  }

  @Test
  public void userOverviewWithUserThatExists_ExpectReturnOverview(){
    ResponseEntity<UserOverviewDto> response = withAuthTestRestTemplate().getForEntity("/overview/{username}", UserOverviewDto.class, Collections.singletonMap("username", "satoshiNakamoto"));
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    UserOverviewDto userOverviewDto = response.getBody();

    assertNotNull(userOverviewDto);
    assertNotNull(userOverviewDto.getId());
    assertEquals(userOverviewDto.getFollowerCount(), 4);
    assertEquals(userOverviewDto.getFollowingCount(), 0);
    assertEquals(userOverviewDto.getTweetCount(), 2);
  }

  @Test
  public void userOverviewWithUserThatDoesntExists_ExpectBadRequest(){
    ResponseEntity<ErrorMessage> response = withAuthTestRestTemplate().getForEntity("/overview/{username}", ErrorMessage.class, Collections.singletonMap("username", "taylorSwift"));
    assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    ErrorMessage errorMessage = response.getBody();
    assertNotNull(errorMessage);
    assertEquals(errorMessage.getMessage(), "Invalid username 'taylorSwift'");
  }

  private List<String> extractUsernames(List<UserDTO> users) {
    return users.stream().map(UserDTO::getUsername).collect(toList());
  }
}
