package com.yourstocks.repository;

import java.util.List;
import java.util.Optional;

import com.yourstocks.model.Stock;

public interface StockRepository {
	List<Stock> findByUserID(Long userId);

	Stock save(Stock stock);

	List<Stock> findAll();

	void deleteById(Long id);

	Optional<Stock> findByStockID(Long id);
}
