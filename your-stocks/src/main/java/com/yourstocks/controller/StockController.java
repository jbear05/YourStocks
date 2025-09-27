package com.yourstocks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourstocks.model.Stock;
import com.yourstocks.model.User;
import com.yourstocks.request.StockRequest;
import com.yourstocks.service.StockService;
import com.yourstocks.service.UserRepository;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	private final StockService stockService;
	private final UserRepository userRepository;
	
	public StockController(StockService stockService, UserRepository userRepository) {
		this.stockService = stockService;
		this.userRepository = userRepository;
	}
	
	// This creates a new stock
	
	@PostMapping
	public ResponseEntity<Stock> createStock(@RequestBody StockRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		
		Stock stock = new Stock();
		stock.setName(request.getName());
		stock.setValue(request.getValue());
		stock.setUser(user);
		
		Stock savedStock = stockService.saveStock(stock);
		return ResponseEntity.ok(savedStock);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return stockService.getStockById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
	
}
