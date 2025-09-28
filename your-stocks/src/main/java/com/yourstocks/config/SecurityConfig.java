package com.yourstocks.config;

// ... necessary imports ...
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            
            .authorizeHttpRequests(authorize -> authorize
                // Allow public access to registration endpoint
                .requestMatchers("/api/users/register").permitAll()
                
                // Allow H2 console access
                .requestMatchers("/h2-console/**").permitAll()
                
                // All other endpoints require a valid JWT token
                .anyRequest().authenticated()
            )
            
            // CRITICAL: Enable OAuth2 Resource Server for JWT token validation
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
            
        // Final configuration steps for compatibility
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}