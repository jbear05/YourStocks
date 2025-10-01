package com.yourstocks.service;

import com.yourstocks.model.Stock;
import com.yourstocks.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Create or update a stock
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // Get a stock by id
    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    // Get all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Get all stocks for a specific user
    public List<Stock> getStocksByUserId(Long userId) {
        return stockRepository.findByUserId(userId);
    }

    // Delete a stock
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
