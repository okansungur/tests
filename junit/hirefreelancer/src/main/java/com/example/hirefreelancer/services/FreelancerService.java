package com.example.hirefreelancer.services;

import com.example.hirefreelancer.model.FreeLancer;
import com.example.hirefreelancer.model.HiringRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FreelancerService {

    private final Map<FreeLancer, Boolean> availabeFreeLancer;

    {
        availabeFreeLancer = new HashMap<>();
        availabeFreeLancer.put(new FreeLancer("1", 1, "java"), false);
        availabeFreeLancer.put(new FreeLancer("2", 5, "python"), false);
        availabeFreeLancer.put(new FreeLancer("3", 2, "nodejs"), false);

    }


    public String findAvailableFreelancerId(HiringRequest hiringRequest) throws Exception {
        String availableFreelancerId = "";

        for (Map.Entry me : availabeFreeLancer.entrySet()) {
            FreeLancer free = (FreeLancer) me.getKey();
            boolean availibility = (boolean) me.getValue();

            if (free.getId() == hiringRequest.getFreelancerId() && availibility == false) {
                availableFreelancerId = free.getId();
                break;
            }

        }
        if (availableFreelancerId.equals("")) {
            throw new Exception();
        }

        return availableFreelancerId;


    }

    public List<FreeLancer> getAvailableFreelancers() {
        List<FreeLancer> lancerList = new ArrayList<>();
        for (Map.Entry me : availabeFreeLancer.entrySet()) {
            FreeLancer free = (FreeLancer) me.getKey();
            boolean availibility = (boolean) me.getValue();
            //System.out.println("Key: "+me.getKey()+ " & Value: " + me.getValue());
            if (availibility == false) {
                lancerList.add(free);

            }

        }
        return lancerList;
    }

    public int getFreelancersCount() {
        List<FreeLancer> lancerList = new ArrayList<>();
        for (Map.Entry me : availabeFreeLancer.entrySet()) {
            FreeLancer free = (FreeLancer) me.getKey();
            boolean availibility = (boolean) me.getValue();

            if (availibility == false) {
                lancerList.add(free);

            }

        }
        return lancerList.size();
    }

    public void hireFreelancer(String freelancerId) throws Exception {


        String hiredFreelancerId = "";

        for (Map.Entry me : availabeFreeLancer.entrySet()) {
            FreeLancer free = (FreeLancer) me.getKey();
            boolean availibility = (boolean) me.getValue();

            if (free.getId().equals(freelancerId) && availibility == false) {
                me.setValue(true);
                hiredFreelancerId = free.getId();

            }

        }

        if (hiredFreelancerId.equals("")) {
            throw new Exception();
        }

    }

    public void cancelContractWithFreelancer(String freelancerId) throws Exception {


        String availableFreelancerId = "";

        for (Map.Entry me : availabeFreeLancer.entrySet()) {
            FreeLancer free = (FreeLancer) me.getKey();

            boolean availibility = (boolean) me.getValue();

            if (free.getId().equals(freelancerId) && availibility) {

                me.setValue(false);
                availableFreelancerId = free.getId();
            }

        }


        if (availableFreelancerId.equals("")) {
            throw new Exception();
        }
    }


}
