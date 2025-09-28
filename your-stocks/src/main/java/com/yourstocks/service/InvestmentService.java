package com.yourstocks.service;

import com.yourstocks.model.User;
import com.yourstocks.model.Investments;
import com.yourstocks.repository.InvestmentRepository;
import com.yourstocks.repository.UserRepository;
import com.yourstocks.request.InvestmentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvestmentService {
    
    private final UserRepository userRepository;
    private final InvestmentRepository investmentRepository;

    // Inject dependencies
    public InvestmentService(UserRepository userRepository, InvestmentRepository investmentRepository) {
        this.userRepository = userRepository;
        this.investmentRepository = investmentRepository;
    }

    @Transactional // Essential: ensures coin deduction and transaction saving are atomic
    public Investments createInvestment(InvestmentRequest request) {
        
        // 1. Fetch Investor
        User investor = userRepository.findById(request.getInvestorId())
            .orElseThrow(() -> new RuntimeException("Investor not found."));

        // 2. Validate Funds
        if (investor.getCareCoins() < request.getAmount()) {
            throw new RuntimeException("Insufficient careCoins for staking.");
        }

        // 3. Deduct Coins
        investor.setCareCoins(investor.getCareCoins() - request.getAmount());
        userRepository.save(investor); // Persist the balance change

        // 4. Record Investment
        Investments newInvestment = new Investments();
        newInvestment.setInvestorId(investor.getId());
        newInvestment.setTargetUserId(request.getTargetUserId());
        newInvestment.setTargetStockName(request.getTargetStockName());
        newInvestment.setStakeAmount(request.getAmount());
        
        return investmentRepository.save(newInvestment);
    }
}