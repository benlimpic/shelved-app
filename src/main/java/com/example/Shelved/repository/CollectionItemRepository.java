package com.example.Shelved.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.CollectionItem;



public interface CollectionItemRepository extends CrudRepository<CollectionItem, Long>{
  Optional<CollectionItem> findByCollectionItemId(Long collectionItemId);
}
