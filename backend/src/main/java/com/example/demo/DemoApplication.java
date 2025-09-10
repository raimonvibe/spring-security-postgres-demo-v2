// src/main/java/com/example/demo/DemoApplication.java

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the Spring Boot application.
 * The @SpringBootApplication annotation enables auto-configuration,
 * component scanning, and other Spring Boot features.
 * It scans for components in the current package and sub-packages.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * The main method launches the Spring Boot application.
     * No custom logic needed here for this demo.
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}