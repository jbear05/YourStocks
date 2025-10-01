package com.yourstocks.controller;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourstocks.dto.StockResponse;
import com.yourstocks.model.Stock;
import com.yourstocks.model.User;
import com.yourstocks.repository.UserRepository;
import com.yourstocks.request.StockRequest;
import com.yourstocks.service.StockService;
import com.yourstocks.service.UserService;
import com.yourstocks.service.CheckInService;
import com.yourstocks.request.CheckInRequest;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	private final StockService stockService;
	private final UserService userService;
	private final UserRepository userRepository;
	private final CheckInService checkInService;
	
	public StockController(StockService stockService, UserService userService, UserRepository userRepository, CheckInService checkInService) {
		this.stockService = stockService;
		this.userService = userService;
		this.userRepository = userRepository;
		this.checkInService = checkInService;
	}
	
	// This creates a new stock
	
	@PostMapping("/create")
	public ResponseEntity<Stock> createStock(@RequestBody StockRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		
		Stock stock = new Stock();
		stock.setName(request.getName());
		stock.setValue(request.getValue());
		stock.setUser(user);
		
		Stock savedStock = stockService.saveStock(stock);
		return ResponseEntity.ok(savedStock);
	}
	
    @GetMapping("/id/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long stockId) {
        return stockService.getStockById(stockId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete stock
    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/checkin")
    public StockResponse checkIn(@AuthenticationPrincipal Jwt jwt, @RequestBody CheckInRequest request) { 
        
    	// Use the Auth0 Subject (sub) or a custom claim for the User ID
        String auth0UserId = jwt.getSubject();
        
        // DEBUG STATEMENT 1: Print the ID extracted from the token
        System.out.println("DEBUG (Controller): Extracted Auth0 Subject: " + auth0UserId); 

		// Find the corresponding User ID in your database and use it in processCheckIn
        Long userId = userService.getUserIdFromAuth0Id(auth0UserId); 
        
        // The service still returns the updated Stock entity
        Stock updatedStock = checkInService.processCheckIn(
            userId,
            request.getStockName(),
            request.getMoodScore()
        );
        
        // CRITICAL: Convert the managed entity to the clean DTO before returning
        return new StockResponse(updatedStock); 
    }
	
}
