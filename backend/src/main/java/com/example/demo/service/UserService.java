// src/main/java/com/example/demo/service/UserService.java

package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * This service implements UserDetailsService, a core Spring Security interface.
 * It's responsible for loading user-specific data from the database during authentication.
 * We use the UserRepository to fetch the user by username and convert it to UserDetails.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor injection for the repository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username (called by Spring Security during login).
     * Throws UsernameNotFoundException if the user doesn't exist.
     * Maps the User's role to a GrantedAuthority (e.g., "ROLE_USER").
     * Uses Spring Security's User builder to create UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert role to authorities (prefix with "ROLE_" as per Spring Security convention)
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );

        // Build and return UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities  // Enabled, non-expired, etc., defaults to true
        );
    }

}