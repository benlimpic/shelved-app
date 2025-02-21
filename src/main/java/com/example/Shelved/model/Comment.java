package com.example.Shelved.model;

import javax.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment {
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long comment_id;
  private Long user_id;
  private Long item_id;
  private Long collection_id;
  private Long comment_parent_id;

  private String comment_text;
  private String comment_date;
  private String comment_time;
  private Boolean comment_is_reply;


  @ManyToOne(fetch = FetchType.LAZY)
  private List<User> comment_likes = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<Comment> comment_replies = new ArrayList<>();

}
