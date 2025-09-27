package com.yourstocks.service;

import java.util.List;

import com.yourstocks.model.Investments;
import com.yourstocks.request.InvestmentRequest;

// Holds logic
// Calls repository and applies methods depending on app specifications (like deductions and calculations)

public interface InvestmentService {
	List<Investments> getAllInvestments();
	Investments createInvestment(InvestmentRequest investment);
}
