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
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long collectionItemId;
  private Long collectionId;
  private Long itemId;
  private ItemType itemType;
  private String itemCommentaryByUser;
  private ItemRating itemRatingByUser;
  private Boolean itemFavorite;


/*MUSIC*/
  private String musicArtist;
  private String musicAlbum;
  private String musicGenre;
  private String musicRelease_date;
  private String musicLabel;
  private String musicLength;
  private String musicDescription;
  private String musicImage;

/*CINEMA*/
  private String cinemaDirector;
  private String cinemaProducer;
  private String cinemaScreenwriter;
  private String cinemaStudio;
  private String cinemaRelease_date;
  private String cinemaLength;
  private String cinemaDescription;
  private String cinemaImage;

/*BOOK*/
  private String bookAuthor;
  private String bookPublisher;
  private String bookGenre;
  private String bookRelease_date;
  private String bookLength;
  private String bookDescription;
  private String bookImage;

/*VIDEOGAME*/
  private String videogameDeveloper;
  private String videogamePublisher;
  private String videogameGenre;
  private String videogameReleaseDate;
  private String videogamePlatform;
  private String videogameDescription;
  private String videogameImage;

/*ART*/
  private String artArtist;
  private String artTitle;
  private String artGenre;
  private String artRelease_date;
  private String artDescription;
  private String artImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private List<User> collectionItemLikedBy = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private List<Comment> collectionItemComments = new ArrayList<>();


}
