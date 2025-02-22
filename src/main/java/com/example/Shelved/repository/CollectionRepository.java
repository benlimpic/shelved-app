package com.example.Shelved.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.Collection;

import com.example.Shelved.model.User;

public interface CollectionRepository extends CrudRepository<Collection, Long>{
  Optional<Collection> findByUserId(Long userId);
  Optional<Collection> findByCollectionId(Long collectionId);
  List<Collection> findAllByUserId(Long userId);
  
}
