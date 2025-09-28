package com.yourstocks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourstocks.model.Stock;

@SuppressWarnings("unchecked")
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	List<Stock> findByUserId(Long userId);
	
	Stock findByUser_IdAndName(Long userId, String name);

	Stock save(Stock stock);

	List<Stock> findAll();

	void deleteById(Long id);

	Optional<Stock> findById(Long id);
}
