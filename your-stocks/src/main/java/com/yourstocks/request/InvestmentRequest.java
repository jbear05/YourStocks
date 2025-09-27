package com.yourstocks.request;

// Data Transfer Object to separate what the cleint sends from entity
public class InvestmentRequest {
	private Long userId;
	private Long stockId;
	private Double amount;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
