package com.example.Shelved.model;

import javax.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "collections")
@NoArgsConstructor
public class Collection {
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long collectionId;
  private String userId;

  private String collectionName;
  private String collectionDescription;
  private String collectionImage;
  private Boolean collectionFavorite;

  @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Collection_Item> collectionItems = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<User> collectionLikedBy = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<Comment> collectionComments = new ArrayList<>();


}
