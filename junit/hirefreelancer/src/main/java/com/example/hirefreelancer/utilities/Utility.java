package com.example.hirefreelancer.utilities;

public class Utility {

    public static final double USD_TO_EUR_RATE = 0.80;

    public static double convertingEuro(double price) {
        return price * USD_TO_EUR_RATE;
    }

}
