package com.yourstocks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yourstocks.model.User;
import com.yourstocks.repository.UserRepository;

import com.yourstocks.model.Stock;
import com.yourstocks.repository.StockRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StockRepository stockRepository; 

    public UserService(UserRepository userRepository, StockRepository stockRepository) {
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional // Ensures the user and stocks are saved together
    public User registerNewUser(String username) {
        
        User newUser = new User(username); 
        // 1. Save the new user to get the ID
        newUser = userRepository.save(newUser); 
        
        // 2. Create and save the stocks
        stockRepository.save(new Stock(newUser, "fitness")); 
        stockRepository.save(new Stock(newUser, "social"));
        stockRepository.save(new Stock(newUser, "career"));

        // 3. CRITICAL STEP: Reload the User and eagerly fetch the stocks
        // We use the new query method to ensure the stocks list is populated 
        // before the method completes and the session closes.
        return userRepository.findByIdWithStocks(newUser.getId())
            .orElseThrow(() -> new RuntimeException("Error fetching newly created user data."));
    }
    
    @Transactional(readOnly = true) // Use a transactional context for reading
    public Optional<User> getUserById(Long userId) {
        
        // Use the custom repository method to fetch the User and the Stocks
        Optional<User> userOptional = userRepository.findByIdWithStocks(userId);
        
        // CRITICAL: Ensure the custom query logic is applied here
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Ensure the lazy loaded list is initialized if you're not using findByIdWithStocks
            // Hibernate.initialize(user.getStocks()); 

            return Optional.of(user);
        }
        
        // If user is not found, return empty Optional
        return Optional.empty(); 
    }

    public List<User> searchUsersByUsername(String query) {
        
    	// TEMPORARY DEBUG: Check what value the service is actually receiving
        System.out.println("DEBUG: Incoming Search Query is: [" + query + "]"); 
    	
        // Final sanity check: if query is null/empty, return empty list
        if (query == null || query.trim().isEmpty()) {
            return java.util.Collections.emptyList();
        }
        
        // Call the guaranteed custom method
        return userRepository.searchByUsername(query);
    }

    public Long getUserIdFromAuth0Id(String auth0Sub) {
        
        // DEBUG STATEMENT 2: Print the value being searched
        System.out.println("DEBUG (Service): Attempting lookup for Auth0 Sub: " + auth0Sub); 

        User user = userRepository.findByAuth0Sub(auth0Sub)
            .orElseThrow(() -> {
                // DEBUG STATEMENT 3: CONFIRMS FAILURE POINT
                System.out.println("DEBUG (Service): Lookup FAILED. Subject is NOT in DB."); 
                return new RuntimeException("User not linked to Auth0 ID: " + auth0Sub);
            });
            
        // DEBUG STATEMENT 4: This line WILL NOT BE REACHED if the code crashes
        System.out.println("DEBUG (Service): Successfully MAPPED Auth0 Sub to User ID: " + user.getId()); 

        return user.getId();
    }
}