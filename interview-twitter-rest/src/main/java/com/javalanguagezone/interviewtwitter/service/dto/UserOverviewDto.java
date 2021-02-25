package com.javalanguagezone.interviewtwitter.service.dto;

import com.javalanguagezone.interviewtwitter.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class UserOverviewDto {

  private Long id;
  private String username;
  private int followerCount;
  private int followingCount;
  private int tweetCount;

  public UserOverviewDto(User user, int tweetCount) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.followerCount = user.getFollowers().size();
    this.followingCount = user.getFollowing().size();
    this.tweetCount = tweetCount;
  }
}
