package com.yourstocks.controller;

import org.springframework.web.bind.annotation.*;

import com.yourstocks.request.CheckInRequest;

import com.yourstocks.service.CheckInService;

import com.yourstocks.model.Stock;

@RestController
@RequestMapping("/api/stocks")
public class CheckInController {

    private final CheckInService checkInService;

    public StockController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/checkin")
    public Stock checkIn(@RequestBody CheckInRequest request) {
        
        // NOTE: In a real app, you'd get the userId from the authenticated user context, 
        // not from the request body. For the MVP, this is acceptable.
        
        return checkInService.processCheckIn(
            request.getUserId(),
            request.getStockName(),
            request.getMoodScore()
        );
    }
}