package com.example.hirefreelancer.services;

import com.example.hirefreelancer.model.HiringRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HiringDAO {

    private static Map<String, HiringRequest> hirings = new HashMap<>();

    public String save(HiringRequest hiringRequest) {
        String id = hiringRequest.getFreelancerId();

        hirings.put(id, hiringRequest);

        return id;
    }

    public HiringRequest get(String id) {


        HiringRequest hiringRequest = null;

        for (Map.Entry me : hirings.entrySet()) {
            String key = me.getKey() + "";

            if (key.equals(id)) {

                hiringRequest = (HiringRequest) me.getValue();
            }

        }

        return hiringRequest;


    }

    public static Map<String, HiringRequest> getHirings() {
        return hirings;
    }

    public void delete(String hiringid) {
        hirings.remove(hiringid);
    }

}
