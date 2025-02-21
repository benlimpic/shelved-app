package com.example.Shelved.model;

import javax.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long userId;
  private String username;
  private String userEmail;
  private String userPassword;
  private String userImage;
  private String userBio;
  private String userLocation;
  private String userBirthday;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Collection> collections = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<User> userFollowing = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<User> userFollowers = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Comment> userComments = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Collection> userLikedCollections = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CollectionItem> userLikedItems = new ArrayList<>();
}
