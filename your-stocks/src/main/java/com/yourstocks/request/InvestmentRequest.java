package com.yourstocks.request;

// Data Transfer Object to separate what the cleint sends from entity
public class InvestmentRequest {
	private Long investorId;
	private Long targetUserId;
	private String targetStockName;
	private Integer amount;
	
	public Long getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}
	public Long getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}
	public String getTargetStockName() {
		return targetStockName;
	}
	public void setTargetStockName(String targetStockName) {
		this.targetStockName = targetStockName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
