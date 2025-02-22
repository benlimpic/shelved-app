package com.example.Shelved.controller;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Shelved.model.Collection;
import com.example.Shelved.model.User;
import com.example.Shelved.repository.CollectionRepository;
import com.example.Shelved.repository.UserRepository;

@RequestMapping("/collections")
@RestController
public class CollectionController {

  private final CollectionRepository collectionRepository;

  public CollectionController(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  @GetMapping("/")
  public List<Collection> getAllCollections() {
    List<Collection> collections = new ArrayList<>();
    collectionRepository.findAll().forEach(collections::add);
    if (collections.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collections not found");
    } else {
      return collections;
    }
  }
  
  @GetMapping("/{collectionId}")
  public Collection getCollectionById(@PathVariable Long collectionID) {
    Optional<Collection> collection = collectionRepository.findByCollectionId(collectionID);
    if (collection.isPresent()) {
      return collection.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found");
    }
  }

  @GetMapping("/user/{userId}")
  public List<Collection> getCollectionsByUserId(@PathVariable Long userId) {
    List<Collection> collections = collectionRepository.findAllByUserId(userId);
    if (collections.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collections not found");
    } else {
      return collections;
    }
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public void createCollection(@RequestBody Collection collection) {
    validateCollection(collection);
    collectionRepository.save(collection);
  }

  @PutMapping("/{collectionId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCollectionInfo(@PathVariable("collectionId") Long collectionId, @RequestBody Collection updatedCollection) {

    Optional<Collection> optionalExistingCollection = this.collectionRepository.findById(collectionId);
    if (optionalExistingCollection.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found");
    }

    Collection existingCollection = optionalExistingCollection.get();

    copyCollectionInfoFrom(updatedCollection, existingCollection);
    collectionRepository.save(existingCollection);
  }

/*-------------------------------------------------------------------------------------------------*/

  private void copyCollectionInfoFrom(Collection updatedCollection, Collection existingCollection) {

    if (ObjectUtils.isEmpty(updatedCollection.getCollectionName())) {
      existingCollection.setCollectionName(updatedCollection.getCollectionName());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionDescription())) {
      existingCollection.setCollectionDescription(updatedCollection.getCollectionDescription());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionImage())) {
      existingCollection.setCollectionImage(updatedCollection.getCollectionImage());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionFavorite())) {
      existingCollection.setCollectionFavorite(updatedCollection.getCollectionFavorite());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionItems())) {
      existingCollection.setCollectionItems(updatedCollection.getCollectionItems());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionLikedBy())) {
      existingCollection.setCollectionLikedBy(updatedCollection.getCollectionLikedBy());
    }
    if (ObjectUtils.isEmpty(updatedCollection.getCollectionComments())) {
      existingCollection.setCollectionComments(updatedCollection.getCollectionComments());
    }
  }

  private void validateCollection(Collection collection) {
    if (ObjectUtils.isEmpty(collection.getCollectionName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection name is required");
    }
    if (ObjectUtils.isEmpty(collection.getUserId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID is required");
    }
  }
}
