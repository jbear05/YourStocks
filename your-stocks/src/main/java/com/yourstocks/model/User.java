package com.yourstocks.model;

/*
This entity will hold the information for each user in a table.
*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marks this class as a JPA entity (a table in the database)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // The primary key for the user

    // Used for registration/login and is tied to the username/email
    // ***Note: For Auth0 integration, this might become a unique 'auth0Id' string
    private String username; 

    // virtual currency balance
    private Integer careCoins; 

    // --- Constructors ---
    // JPA requires a default no-argument constructor
    public User() {
        // Initialize careCoins upon creation (100 is your starter amount)
        this.careCoins = 100; 
    }

    // Constructor for creating a new user with a username
    public User(String username) {
        this.username = username;
        this.careCoins = 100; // Starter amount
    }

    // --- Getters and Setters ---
    public Long getId() {
    	return this.id;
    }
    
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Integer getCareCoins() {
		return this.careCoins;
	}
	public void setCareCoins(Integer careCoins) {
		this.careCoins = careCoins;
	}
}
