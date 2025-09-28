package com.yourstocks.dto; // Create a new package for DTOs

import com.yourstocks.model.Stock;

public class StockResponse {
    private Long id;
    private String name;
    private Double currentValue;
    private Long userId; // The ID of the associated user (no User object needed)

    // Constructors (for easy mapping) and Getters/Setters go here...
    
    // Example Constructor for mapping:
    public StockResponse(Stock stock) {
        this.setId(stock.getId());
        this.setName(stock.getName());
        this.setCurrentValue(stock.getValue());
        this.setUserId(stock.getUser().getId()); // Access only the ID!
    }

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

	public Double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
