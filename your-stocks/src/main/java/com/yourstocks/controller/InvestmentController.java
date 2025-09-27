package com.yourstocks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourstocks.model.Investments;
import com.yourstocks.request.InvestmentRequest;
import com.yourstocks.service.InvestmentService;

// This is the entry point for HTTP requests
// Receives data from postman, calls service, returns response

// Rest API Controller
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
	
	private final InvestmentService investmentService;
	
	public InvestmentController(InvestmentService investmentService) {
		this.investmentService = investmentService;
	}
	
	// Get requests run this method
	// Returns as JSON
	@GetMapping
	public List<Investments> getInvestments() {
		return investmentService.getAllInvestments();
	}
	
	// POST requests run this method
	// turns JSON body from request -> Investments object
	@PostMapping
	public Investments addInvestment(@RequestBody InvestmentRequest investment) {
		return investmentService.createInvestment(investment);
	}
}
