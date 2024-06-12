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
 * The PublicUserController class represents a RESTful API controller for retrieving user data.
 * It handles HTTP requests related to user resources and interacts with a UserRepository.
 */
@RestController
@RequestMapping("/public/users")
@AllArgsConstructor
public class PublicUserController {

    private final UserRepository userRepository;

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws EntityNotFoundException if no user was found with the specified ID
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s not found", id)));
    }

    /**
     * Retrieves all users from the database.
     * @return a list of User objects representing all users in the database
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user in the database.
     * @param user the user object to be created
     * @return the created user object
     */
    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user in the database.
     * @param user the User object representing the updated user
     * @return the updated User object
     */
    @RequestMapping(method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes one or more users from the database based on the provided user IDs.
     * @param ids List of Long representing the IDs of the users to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody List<Long> ids) {
        userRepository.deleteAllById(ids);
    }

    /**
     * Handles the EntityNotFoundException and returns a ResponseEntity with the appropriate status code
     * and error message.
     *
     * @param ex the EntityNotFoundException that was thrown
     * @return a ResponseEntity containing the error message with HTTP status code 404 (Not Found)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}