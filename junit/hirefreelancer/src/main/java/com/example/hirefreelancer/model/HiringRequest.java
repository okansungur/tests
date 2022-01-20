package com.example.hirefreelancer.model;

import java.time.LocalDate;

public class HiringRequest {

    private String clientId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean paymentmade;
    private int experienceyears;
    private String freelancerId;

    public HiringRequest(String clientId, LocalDate dateFrom, LocalDate dateTo, boolean paymentmade, int experienceyears, String freelancerId) {
        this.clientId = clientId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.paymentmade = paymentmade;
        this.experienceyears = experienceyears;
        this.freelancerId = freelancerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }


    public boolean isPaymentmade() {
        return paymentmade;
    }

    public void setPaymentmade(boolean paymentmade) {
        this.paymentmade = paymentmade;
    }

    public int getExperienceyears() {
        return experienceyears;
    }

    public void setExperienceyears(int experienceyears) {
        this.experienceyears = experienceyears;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }
}
