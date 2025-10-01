package com.yourstocks.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Direct foreign key to the User
    private Long userId; 
    
    // The specific stock being updated (e.g., "fitness", "career")
    private String stockName; 
    
    // The input that drives the stock change
    private Integer moodScore; // Value from 1 (bad) to 10 (great)

    // Timestamp for historical tracking
    private Instant timestamp; 

    // Constructors
    public CheckIn() {
        userId = (long) 0;
        stockName = "";
        moodScore = 0;
        this.setTimestamp(Instant.now());
    }
    public CheckIn(Long userId, String stockName, Integer moodScore) {
        this.setUserId(userId);
        this.setStockName(stockName);
        this.setMoodScore(moodScore);
        this.setTimestamp(Instant.now());
    }
    
	
    // --- Getters and Setters ---
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getMoodScore() {
		return moodScore;
	}
	public void setMoodScore(Integer moodScore) {
		this.moodScore = moodScore;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

    
}
