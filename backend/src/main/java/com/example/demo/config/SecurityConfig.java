// src/main/java/com/example/demo/config/SecurityConfig.java

package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * This configuration class sets up Spring Security for the application.
 * @EnableWebSecurity enables Spring Security's web security support.
 * @Configuration indicates this is a source of bean definitions.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // We inject the UserService, which implements UserDetailsService for loading users from the DB.
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * Defines the security filter chain, which configures HTTP security.
     * This replaces the old WebSecurityConfigurerAdapter in Spring Security 5.7+ / Spring Boot 2.7+.
     * Here, we specify which requests require authentication and set up form login/logout.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS with custom configuration (allows requests from frontend origin)
            .cors(Customizer.withDefaults())
            // Configure request authorization
            .authorizeHttpRequests((requests) -> requests
                // Allow anonymous access to /login to avoid redirect loop
                .requestMatchers("/login").anonymous()
                // Protect the /home endpoint - requires authentication
                .requestMatchers("/home").authenticated()
                // Any other requests require authentication
                .anyRequest().authenticated()
            )
            // Enable form-based login with default settings (login page at /login)
            .formLogin((form) -> form
                .loginPage("/login")  // Default login page URL
                .loginProcessingUrl("/perform_login")  // Separate URL for POST to avoid loop
                .defaultSuccessUrl("/home", true)  // Redirect to /home after successful login
                .permitAll()  // Allow anyone to access the login page
            )
            // Enable logout support (default logout URL is /logout)
            .logout((logout) -> logout
                .permitAll()  // Allow anyone to logout
            )
            // Disable CSRF for simplicity in this demo; enable in production!
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    /**
     * Custom CORS configuration to allow requests from the frontend (e.g., localhost:3000).
     * This is optional but provides more control than defaults.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));  // Allow frontend origin
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);  // Important for cookies/session auth

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Bean for password encoding using BCrypt.
     * Spring Security requires a PasswordEncoder to hash/verify passwords.
     * Never store plain-text passwords!
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Note: We don't need to explicitly define AuthenticationManager or UserDetailsService here,
    // as Spring Security will autowire our UserService (which implements UserDetailsService)
    // into the authentication provider automatically.
}
