package com.yourstocks.request;

public class CheckInRequest {
    private Long userId;
    private String stockName;
    private Integer moodScore;
    
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
}
