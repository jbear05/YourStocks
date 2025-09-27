package com.yourstocks.service;

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
        // 1. Create and save the new User
        User newUser = new User(username); 
        newUser = userRepository.save(newUser);

        // 2. Create the 3 starter stocks (as required by the PRD)
        // if you see this Sam feel free to change the constructor to fit whatever
        // you made it to be
        stockRepository.save(new Stock(newUser.getId(), "fitness"));
        stockRepository.save(new Stock(newUser.getId(), "social"));
        stockRepository.save(new Stock(newUser.getId(), "career"));

        return newUser;
    }
}