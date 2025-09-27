package com.yourstocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourstocks.model.Investments;

// This interface talks directly with/to the database

public interface InvestmentRepository extends JpaRepository<Investments, Long> {
	
	List<Investments> findByUserID(Long userId);
}
