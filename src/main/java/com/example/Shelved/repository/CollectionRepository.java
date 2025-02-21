package com.example.Shelved.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.Collection;

public interface CollectionRepository extends CrudRepository<Collection, Long>{
  
}
