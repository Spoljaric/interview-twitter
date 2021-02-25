package com.javalanguagezone.interviewtwitter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static java.util.Collections.singletonList;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
@ToString(exclude = {"following", "followers"})
@EqualsAndHashCode(exclude = {"following", "followers"})
public class User implements UserDetails {
  public static final List<SimpleGrantedAuthority> AUTHORITIES = singletonList(new SimpleGrantedAuthority("USER"));

  protected static final int ZERO_LENGTH = 0;
  protected static final int USERNAME_MAX_LENGTH = 32;
  protected static final int FULL_NAME_MAX_LENGTH = 128;
  protected static final int PASSWORD_MAX_LENGTH = 256;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String fullName;

  @JsonIgnore
  @ManyToMany
  private Set<User> following = new HashSet<>();

  @JsonIgnore
  @ManyToMany(mappedBy = "following")
  private Set<User> followers = new HashSet<>();

  @JsonIgnore
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String password, String fullName) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
  }

  public void addFollowing(User... users){
    following.addAll(Arrays.asList(users));
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AUTHORITIES;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public boolean isValid() {
    return username != null && password != null && fullName != null &&
      username.length() > ZERO_LENGTH && password.length() > ZERO_LENGTH && fullName.length() > ZERO_LENGTH &&
      username.length() <= USERNAME_MAX_LENGTH && password.length() <= PASSWORD_MAX_LENGTH && fullName.length() <= FULL_NAME_MAX_LENGTH;
  }
}
