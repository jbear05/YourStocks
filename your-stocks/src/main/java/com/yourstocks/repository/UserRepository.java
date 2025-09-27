package com.yourstocks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourstocks.model.User;

@Repository // Marks this as a Spring component for data access
public interface UserRepository extends JpaRepository<User, Long> {
    
    //find a user by their username
    Optional<User> findByUsername(String username); 
    
    //find a user by their username
    Optional<User> findById(Long id); 
}
