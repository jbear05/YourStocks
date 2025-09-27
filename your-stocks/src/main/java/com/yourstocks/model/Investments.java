package com.yourstocks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investments")
public class Investments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// This checks the amount of CareCoins invested
	@Column(nullable = false)
	private Double amount;
	
	// This shows timestamp of investments
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	// This just shows and maps the relationship to the user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
