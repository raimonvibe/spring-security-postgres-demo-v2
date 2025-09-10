// src/main/java/com/example/demo/controller/HomeController.java

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a simple REST controller providing a protected endpoint.
 * The /home endpoint is secured by Spring Security, so it requires authentication to access.
 * It demonstrates a basic protected resource.
 */
@RestController
public class HomeController {

    /**
     * GET mapping for /home.
     * Returns a welcome message if the user is authenticated.
     * Spring Security will handle redirection to login if not authenticated.
     */
    @GetMapping("/home")
    public String home() {
        return "Welcome to the protected home page! You are authenticated.";
    }

}