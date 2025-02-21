package com.example.Shelved.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.Collection_Item;



public interface CollectionItemRepository extends CrudRepository<Collection_Item, Long>{
  
}
