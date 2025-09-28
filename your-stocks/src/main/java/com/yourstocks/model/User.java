package com.yourstocks.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

/*
This entity will hold the information for each user in a table.
*/

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Marks this class as a JPA entity (a table in the database)
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // The primary key for the user

    // Used for registration/login and is tied to the username/email
    // ***Note: For Auth0 integration, this might become a unique 'auth0Id' string
    private String username; 

    // virtual currency balance
    private Integer careCoins; 
    
    // Defines the relationship from User to multiple Stocks
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stocks = new ArrayList<>(); 
    
    @Column(name = "auth0_sub", unique = true) // Store the unique Auth0 identifier (Subject)
    private String auth0Sub;

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
	
	public List<Stock> getStocks() {
        return stocks;
    }
}
