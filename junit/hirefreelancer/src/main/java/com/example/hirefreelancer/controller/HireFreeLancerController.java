package com.example.hirefreelancer.controller;


import com.example.hirefreelancer.services.FreelancerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HireFreeLancerController {

    private FreelancerService freelancerService;

    @RequestMapping("/")
    public String index() {
        return "Hi. We have " + freelancerService.getFreelancersCount() + " available freelancers";
    }

    public HireFreeLancerController(FreelancerService freelancerService) {
        this.freelancerService=freelancerService;
    }
}
