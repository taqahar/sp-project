package com.example.supralogproject.repository;

import com.example.supralogproject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for user data.
 * Extends {@link MongoRepository} for operations on User entities.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Retrieves all users sorted by last name in ascending order.
     *
     * @return List of {@link User} sorted by last name in ascending order
     */
    List<User> findAllByOrderByLastNameAsc();

    /**
     * Retrieves all users sorted by last name in descending order.
     *
     * @return List of {@link User} sorted by last name in descending order
     */
    List<User> findAllByOrderByLastNameDesc();
}
