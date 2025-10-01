package com.yourstocks.request;

public class RegistrationRequest {
    
    // The username the client wants to register with
    private String username;

    // We might also include a simple password or Auth0-specific token later
    // private String password; 
    
    // --- Getters and Setters ---
    
    // Getter is essential for the service/controller to read the data
    public String getUsername() {
        return username;
    }

    // Setter is essential for Spring Boot's JSON mapper (Jackson) to write the data
    public void setUsername(String username) {
        this.username = username;
    }
    //other fields tbd
}
