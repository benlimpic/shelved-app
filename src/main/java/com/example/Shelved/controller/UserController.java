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

import com.example.Shelved.model.User;
import com.example.Shelved.repository.UserRepository;

@RequestMapping("/users")
@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/{user_name}")
  public User getUserByUserName(@PathVariable String user_name) {
    Optional<User> user = userRepository.findByUsername(user_name);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
  }
  
  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody User user) {
    validateUser(user);
    userRepository.save(user);
  }

  @PutMapping("/{username}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUserInfo(@PathVariable("username") String username, @RequestBody User updatedUser) {

    Optional<User> optionalExistingUser = this.userRepository.findByUsername(username);
    if (optionalExistingUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    User existingUser = optionalExistingUser.get();

    copyUserInfoFrom(updatedUser, existingUser);
    userRepository.save(existingUser);
  }


  /*----------------------------------------------------------------------------------------- */

  
  private void copyUserInfoFrom(User updatedUser, User existingUser) {

    if (ObjectUtils.isEmpty(updatedUser.getUsername())) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username is required");
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserEmail())) {
      existingUser.setUserEmail(updatedUser.getUserEmail());
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserPassword())) {
      existingUser.setUserPassword(updatedUser.getUserPassword());
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserImage())) {
      existingUser.setUserImage(updatedUser.getUserImage());
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserBio())) {
      existingUser.setUserBio(updatedUser.getUserBio());
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserLocation())) {
      existingUser.setUserLocation(updatedUser.getUserLocation());
    }

    if (!ObjectUtils.isEmpty(updatedUser.getUserBirthday())) {
      existingUser.setUserBirthday(updatedUser.getUserBirthday());
    }
  }


  private void validateUser(User user) {
    validateUsername(user.getUsername());

    Optional<User> optionalUser = this.userRepository.findByUsername(user.getUsername());
    if (optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }
  }

  private void validateUsername(String username) {
    if (ObjectUtils.isEmpty(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

}
