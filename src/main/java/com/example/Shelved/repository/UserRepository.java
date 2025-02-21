package com.example.Shelved.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Shelved.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsername(String user_name);
}
