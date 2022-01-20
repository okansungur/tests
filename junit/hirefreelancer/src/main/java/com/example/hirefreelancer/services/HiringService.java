package com.example.hirefreelancer.services;

import com.example.hirefreelancer.model.FreeLancer;
import com.example.hirefreelancer.model.HiringRequest;
import com.example.hirefreelancer.utilities.HireException;
import com.example.hirefreelancer.utilities.Utility;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HiringService {

    private DepositService depositService;
    private FreelancerService freelancerService;
    private HiringDAO hiringDAO;
    private Notification notification;


    private final static double dailyminprice = 30.0;


    public int getAvailableFreelancerExperienceYearsCount() {
        int sum = 0;
        List<FreeLancer> lancerList = freelancerService.getAvailableFreelancers();
        for (int i = 0; i < lancerList.size(); i++) {
            FreeLancer freeLancer = lancerList.get(i);
            sum += freeLancer.getExperience();
        }

        return sum;
    }

    public double calculatePayment(HiringRequest hiringRequest) {
        long days = ChronoUnit.DAYS.between(hiringRequest.getDateFrom(), hiringRequest.getDateTo());
        return dailyminprice * hiringRequest.getExperienceyears() * days;
    }

    public double paymentConvertUSDtoEuro(HiringRequest hiringRequest) {
        long days = ChronoUnit.DAYS.between(hiringRequest.getDateFrom(), hiringRequest.getDateTo());


        double total= hiringRequest.getExperienceyears() * days*dailyminprice;

        return Utility.convertingEuro(total);
    }


    public String requestHiring(HiringRequest hiringRequest) throws Exception {


        String freelancerId = freelancerService.findAvailableFreelancerId(hiringRequest);


        double price = calculatePayment(hiringRequest);

        if (!hiringRequest.isPaymentmade()) {
            depositService.pay(hiringRequest, price);

        }


        String hiringfreelancerid = hiringDAO.save(hiringRequest);
        freelancerService.hireFreelancer(freelancerId);
        notification.sendNotificationConfirmation(hiringfreelancerid);
        return hiringfreelancerid;
    }


    public void cancelHiring(HiringRequest request) throws Exception {
        String id = request.getFreelancerId();


        if (request != null) {
            freelancerService.cancelContractWithFreelancer(id);
            hiringDAO.delete(id);
        } else {
            throw new HireException();
        }
    }

    public HiringService(DepositService depositService, FreelancerService freelancerService, HiringDAO hiringDAO, Notification notify) {
        super();
        this.depositService = depositService;
        this.freelancerService = freelancerService;
        this.hiringDAO = hiringDAO;
        this.notification = notify;
    }


}
