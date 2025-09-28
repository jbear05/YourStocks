package com.yourstocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourstocks.model.Investments;

// This interface talks directly with/to the database
@Repository
public interface InvestmentRepository extends JpaRepository<Investments, Long> {
	
	List<Investments> findByTargetUserId(Long targetUserId);
}
