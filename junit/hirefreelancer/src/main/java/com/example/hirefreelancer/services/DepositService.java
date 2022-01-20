package com.example.hirefreelancer.services;

import com.example.hirefreelancer.utilities.HireException;
import com.example.hirefreelancer.model.HiringRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class DepositService {

    private final Map<String, Double> deposits = new HashMap<>();

    public String pay(HiringRequest hiringRequest, double price) {

        if (price < 10) {
            throw new HireException();
        }
        String freelancerId = hiringRequest.getFreelancerId();
        deposits.put(freelancerId, price);
        return freelancerId;
    }


}
