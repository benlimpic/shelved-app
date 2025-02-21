package com.example.Shelved.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{
  
}
