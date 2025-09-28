package com.yourstocks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


@Entity
@Table(name= "stocks")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Stock type with different "portfolio's" of user
	// Default portfolio's
	@Column(nullable = false)
	private String name;
	
	
	@Column(nullable = false)
	private Double currentValue;
	
	
	// Many stocks for one user - many to one relationship
	// Joining columns together with foreign key user_id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("stocks")
    private User user;
	
	public Stock() {
		this.user = null;
		this.name = "";
		this.currentValue = 100.0;
	}
	
	public Stock(User user, String name) {
		this.user = user;
		this.name = name;
		this.currentValue = 100.0;
	}

	
	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return currentValue;
	}

	public void setValue(Double value) {
		this.currentValue = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
