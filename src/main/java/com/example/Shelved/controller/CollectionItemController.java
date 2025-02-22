package com.example.Shelved.controller;

import java.util.Optional;

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

import com.example.Shelved.enums.ItemRating;
import com.example.Shelved.enums.ItemType;
import com.example.Shelved.model.CollectionItem;
import com.example.Shelved.repository.CollectionItemRepository;

@RequestMapping("/collectionItems")
@RestController
public class CollectionItemController {
  
  private final CollectionItemRepository collectionItemRepository;

  public CollectionItemController(CollectionItemRepository collectionItemRepository) {
    this.collectionItemRepository = collectionItemRepository;
  }

  @GetMapping("/{id}")
  public CollectionItem getCollectionItem(@PathVariable Long collectionItemId) {
    Optional<CollectionItem> collectionItem = collectionItemRepository.findByCollectionItemId(collectionItemId);
    if (collectionItem.isPresent()) {
      return collectionItem.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CollectionItem not found");
    }
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public void createCollectionItem(@RequestBody CollectionItem collectionItem) {
    validateCollectionItem(collectionItem);
    collectionItemRepository.save(collectionItem);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCollectionItem(@PathVariable("id") Long id, @RequestBody CollectionItem updatedCollectionItem) {

    Optional<CollectionItem> optionalExistingCollectionItem = this.collectionItemRepository.findByCollectionItemId(id);
    if (optionalExistingCollectionItem.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CollectionItem not found");
    }

    CollectionItem existingCollectionItem = optionalExistingCollectionItem.get();

    copyCollectionItemInfoFrom(updatedCollectionItem, existingCollectionItem);
    collectionItemRepository.save(existingCollectionItem);
  }

  /*------------------------------------------------------------------------------------------------ */

  private void validateCollectionItem(CollectionItem collectionItem) {
    if (ObjectUtils.isEmpty(collectionItem)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CollectionItem must not be null");
    }
  }

  private void copyCollectionItemInfoFrom(CollectionItem updatedCollectionItem, CollectionItem existingCollectionItem) {

    if (ObjectUtils.isEmpty(updatedCollectionItem.getCollectionItemId())) {
      existingCollectionItem.setCollectionItemId(updatedCollectionItem.getCollectionItemId());
    }

    if (ObjectUtils.isEmpty(updatedCollectionItem.getCollectionId())) {
      existingCollectionItem.setCollectionId(updatedCollectionItem.getCollectionId());
    }

    if  (ObjectUtils.isEmpty(updatedCollectionItem.getUserId())) {
      existingCollectionItem.setUserId(updatedCollectionItem.getUserId());
    }

    if (ObjectUtils.isEmpty(updatedCollectionItem.getItemType())) {
      existingCollectionItem.setItemType(updatedCollectionItem.getItemType());
    }

    if (ObjectUtils.isEmpty(updatedCollectionItem.getItemCommentaryByUser())) {
      existingCollectionItem.setItemCommentaryByUser(updatedCollectionItem.getItemCommentaryByUser());
    }

    if (ObjectUtils.isEmpty(updatedCollectionItem.getItemRatingByUser())) {
      existingCollectionItem.setItemRatingByUser(updatedCollectionItem.getItemRatingByUser());
    }

    if (ObjectUtils.isEmpty(updatedCollectionItem.getItemFavorite())) {
      existingCollectionItem.setItemFavorite(updatedCollectionItem.getItemFavorite());
    }
  }
  
}
