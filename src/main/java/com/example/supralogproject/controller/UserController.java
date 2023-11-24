package com.example.supralogproject.controller;

import com.example.supralogproject.dto.UserDTO;
import com.example.supralogproject.model.User;
import com.example.supralogproject.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controller for user operations.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Creates a new user.
     * Uses {@link UserService#createUser(UserDTO)} to create the user and logs the process.
     *
     * @param userDTO Data Transfer Object for user
     * @return {@link ResponseEntity} with the created User or error status
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        logger.info("Creating user");
        Optional<User> user = userService.createUser(userDTO);
        if(user.isPresent()) {
            logger.info("User created successfully");
            return ResponseEntity
                    .created(URI.create("/users/" + user.get().getId()))
                    .body(user.get());
        }
        logger.error("Error while creating user");
        return ResponseEntity.internalServerError().build();
    }

    /**
     * Retrieves a list of all users.
     * Sorts users based on the specified order and logs the process.
     *
     * @param sortOrder Order for sorting users by lastName
     * @return {@link ResponseEntity} with the list of Users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "lastNameSortOrder", defaultValue = "asc") String sortOrder) {
        logger.info("Listing users with sort order: " + sortOrder);
        List<User> users = userService.getAllUsers(sortOrder);
        logger.info("Users fetched successfully");
        return ResponseEntity.ok(users);
    }


    /**
     * Gets a specific user by ID.
     * Fetches the user using the provided ID and logs the process.
     *
     * @param id Identifier of the user
     * @return {@link ResponseEntity} with the User or not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        logger.info("Getting user for id: " + id);
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            logger.info("User fetched successfully");
            return ResponseEntity.ok(user.get());
        }
        logger.error("User not found for id: " + id);
        return ResponseEntity.notFound().build();
    }
}
