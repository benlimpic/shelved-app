package com.example.Shelved.model;
import com.example.Shelved.enums.ItemType;
import com.example.Shelved.enums.ItemRating;

import javax.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "collection_items")
@NoArgsConstructor
public class Collection_Item {
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long collection_item_id;
  private Long collection_id;
  private Long item_id;
  private ItemType item_type;
  private ItemRating item_rating_by_user;
  private String item_commentary_by_user;

/* ITEM DATA TO BE PULLED FROM API */

/*MUSIC*/
  private String music_artist;
  private String music_album;
  private String music_genre;
  private String music_release_date;
  private String music_label;
  private String music_length;
  private String music_description;
  private String music_image;

/*MOVIE*/
  private String movie_director;
  private String movie_producer;
  private String movie_screenwriter;
  private String movie_studio;
  private String movie_release_date;
  private String movie_length;
  private String movie_description;
  private String movie_image;

/*BOOK*/
  private String book_author;
  private String book_publisher;
  private String book_genre;
  private String book_release_date;
  private String book_length;
  private String book_description;
  private String book_image;

/*VIDEOGAME*/
  private String videogame_developer;
  private String videogame_publisher;
  private String videogame_genre;
  private String videogame_release_date;
  private String videogame_platform;
  private String videogame_description;
  private String videogame_image;

/*ART*/
  private String art_artist;
  private String art_title;
  private String art_genre;
  private String art_release_date;
  private String art_description;
  private String art_image;

  @ManyToOne(fetch = FetchType.LAZY)
  private List<User> collection_item_liked_by = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<Comment> collection_item_comments = new ArrayList<>();


}
