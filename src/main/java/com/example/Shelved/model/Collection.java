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
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long collection_id;
  private String user_id;

  private String collection_name;
  private String collection_description;
  private String collection_image;
  
  private Enum display_style;


  @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Collection_Item> collection_items = new ArrayList<>();

}
