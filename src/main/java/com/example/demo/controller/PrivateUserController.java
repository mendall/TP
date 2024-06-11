package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The PrivateUserController class represents a RESTful API controller for managing user entities.
 * It handles HTTP requests related to user resources and interacts with a UserRepository.
 */
@RestController
@RequestMapping("/private/users")
@AllArgsConstructor
public class PrivateUserController {

    private final UserRepository userRepository;

    /**
     * Creates a new user in the database.
     * @param user the user object to be created
     * @return the created user object
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user in the database.
     * @param user the User object representing the updated user
     * @return the updated User object
     */
    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes one or more users from the database based on the provided user IDs.
     * @param ids List of Long representing the IDs of the users to be deleted
     */
    @DeleteMapping
    public void deleteUser(@RequestBody List<Long> ids) {
        userRepository.deleteAllById(ids);
    }

    /**
     * Retrieves a user by their name.
     *
     * @param name the Name of the user to retrieve
     * @return the user with the specified ID
     * @throws EntityNotFoundException if no user was found with the specified Name
     */
    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userRepository.findUserByName(name).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with name %s not found", name)));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}