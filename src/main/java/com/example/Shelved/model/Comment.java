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
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long commentId;
  private Long userId;
  private Long itemId;
  private Long collectionId;
  private Long commentParentId;

  private String commentText;
  private String commentDate;
  private String commentTime;
  private Boolean commentIsReply;


  @ManyToOne(fetch = FetchType.LAZY)
  private List<User> commentLikes = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<Comment> commentReplies = new ArrayList<>();

}
