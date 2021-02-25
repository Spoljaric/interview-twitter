package com.javalanguagezone.interviewtwitter.service;

import com.javalanguagezone.interviewtwitter.domain.User;
import com.javalanguagezone.interviewtwitter.repository.TweetRepository;
import com.javalanguagezone.interviewtwitter.repository.UserRepository;
import com.javalanguagezone.interviewtwitter.service.dto.UserDTO;
import com.javalanguagezone.interviewtwitter.service.dto.UserOverviewDto;
import com.javalanguagezone.interviewtwitter.service.dto.UserRegistrationDto;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private TweetRepository tweetRepository;

  public UserService(UserRepository userRepository, TweetRepository tweetRepository) {
    this.userRepository = userRepository;
    this.tweetRepository = tweetRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUser(username);
    if(user == null)
      throw new UsernameNotFoundException(username);
    return user;
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowing(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowing());
  }

  @Transactional
  public Collection<UserDTO> getUsersFollowers(Principal principal) {
    User user = getUser(principal.getName());
    return convertUsersToDTOs(user.getFollowers());
  }

  @Transactional
  public void userRegistration(UserRegistrationDto userRegistrationDto){
    if(userRepository.countByUsername(userRegistrationDto.getUsername()) > 0)
      throw new UserAlreadyIsRegisteredException(userRegistrationDto.getUsername());
    User registerUser = new User(userRegistrationDto.getUsername(), userRegistrationDto.getPassword(), userRegistrationDto.getFullName());
    if(!registerUser.isValid())
      throw new InvalidUserRegistrationException(userRegistrationDto.getUsername());
    userRepository.save(registerUser);
  }

  @Transactional
  public UserOverviewDto getUserOverview(String username){
    User user = getUser(username);
    if(user == null)
      throw new UsernameNotFoundException(username);
    return new UserOverviewDto(user, tweetRepository.countByAuthor(user));
  }

  public static class UserAlreadyIsRegisteredException extends RuntimeException {

    @Getter
    private String username;

    private UserAlreadyIsRegisteredException(String username) {
      super(username);
      this.username = username;
    }
  }

  public static class InvalidUserRegistrationException extends RuntimeException {

    @Getter
    private String username;

    private InvalidUserRegistrationException(String username) {
      super(username);
      this.username = username;
    }
  }

  private User getUser(String username) {
    return userRepository.findOneByUsername(username);
  }

  private List<UserDTO> convertUsersToDTOs(Set<User> users) {
    return users.stream().map(UserDTO::new).collect(toList());
  }
}
