package com.yourstocks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yourstocks.model.User;

@Repository // Marks this as a Spring component for data access
public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE CONCAT('%', LOWER(:query), '%')")
	List<User> searchByUsername(@Param("query") String query);
    
    //find a user by their username
    Optional<User> findById(Long id); 
    
    // HQL/JPQL query to fetch User AND their Stocks in one go
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.stocks WHERE u.id = :id")
    Optional<User> findByIdWithStocks(@Param("id") Long id); 
    
    //find user by auth0
    Optional<User> findByAuth0Sub(String auth0Sub);
}
