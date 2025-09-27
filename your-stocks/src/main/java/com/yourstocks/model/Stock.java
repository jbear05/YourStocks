package com.yourstocks.model;

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
	private Double value;
	
	
	// Many stocks for one user - many to one relationship
	// Joining columns together with foreign key user_id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	
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
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
