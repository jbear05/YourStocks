package com.yourstocks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yourstocks.repository.CheckInRepository;
import com.yourstocks.repository.StockRepository;

import com.yourstocks.model.CheckIn;
import com.yourstocks.model.Stock;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final StockRepository stockRepository;

    // Constructor injection
    public CheckInService(CheckInRepository checkInRepository, StockRepository stockRepository) {
        this.checkInRepository = checkInRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional // Ensures both save (log) and update (stock) succeed or fail together
    public Stock processCheckIn(Long userId, String stockName, Integer moodScore) {
        
        // 1. Log the Check-in Entry
        CheckIn checkIn = new CheckIn(userId, stockName, moodScore);
        checkInRepository.save(checkIn);

        // 2. Retrieve the Target Stock
        Stock stock = stockRepository.findByUser_IdAndName(userId, stockName);
        if (stock == null) {
            // Handle error: stock not found (e.g., throw a custom exception)
            throw new RuntimeException("Stock not found for user: " + userId + " and stock: " + stockName);
        }

        // 3. Apply the MVP Stock Value Calculation Logic
        // Formula: New Value = Old Value + (Mood Score - 5)
        // This is simple: >5 is a gain, <5 is a loss, 5 is neutral.
        double delta = (double)moodScore - 5.0; // e.g., if mood=8, delta=3.0

        double newStockValue = stock.getValue() + delta;
        
        // OPTIONAL: Prevent stock value from going below 0
        newStockValue = Math.max(0.0, newStockValue); 

        // 4. Update the Stock Value and Save
        stock.setValue(newStockValue);
        return stockRepository.save(stock); // Save and return the updated stock
    }
}
