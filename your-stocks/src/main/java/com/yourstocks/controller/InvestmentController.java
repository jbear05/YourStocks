package com.yourstocks.controller;

import org.springframework.http.ResponseEntity;
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
	
    @PostMapping("/stake")
    public ResponseEntity<Investments> stake(@RequestBody InvestmentRequest request) {
        try {
            Investments newStake = investmentService.createInvestment(request);
            return ResponseEntity.ok(newStake);
        } catch (RuntimeException e) {
            // Handle custom business logic errors (e.g., Insufficient funds)
            return ResponseEntity.badRequest().body(null); // Simple error response for MVP
        }
    }
}
