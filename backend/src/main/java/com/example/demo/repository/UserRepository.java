// src/main/java/com/example/demo/repository/UserRepository.java

package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is the JPA repository interface for the User entity.
 * It extends JpaRepository, providing CRUD operations out-of-the-box.
 * We add a custom query method to find a user by username, which is crucial for authentication.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom method to find a User by their username.
     * Returns an Optional to handle cases where the user might not exist.
     */
    Optional<User> findByUsername(String username);

}