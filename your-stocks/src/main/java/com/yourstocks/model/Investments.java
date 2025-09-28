package com.yourstocks.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "investments")
public class Investments {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user placing the bet (Investor)
    @Column(name = "investor_id", nullable = false)
    private Long investorId;

    // The user/stock being bet on (Target)
    @Column(name = "target_user_id", nullable = false)
    private Long targetUserId;

    @Column(nullable = false)
    private String targetStockName; // e.g., "fitness" or "career"

    @Column(nullable = false)
    private Integer stakeAmount;

    @Column(nullable = false)
    private Instant timestamp = Instant.now();

	public Long getId() {
		return id;
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long userId) {
		this.investorId = userId;
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
	
	public void setStakeAmount(Integer stakeAmount) {
		this.stakeAmount = stakeAmount;
	}
	
	
	
	
}
