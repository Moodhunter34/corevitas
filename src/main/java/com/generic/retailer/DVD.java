package com.generic.retailer;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DVD implements Item {
    private static final double DVD_COST = 15;

    @Override
    public double calculateCost(int quantity) {
        int numberOfDVDs = quantity / 2 + quantity % 2;
        double dvdCost = numberOfDVDs * DVD_COST;

        if (quantity % 2 == 1 && LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
            dvdCost -= 0.2 * DVD_COST;
        }

        return dvdCost;
    }

    @Override
    public void displayCost() {
        System.out.println("cost of dvd = " + calculateCost(1));
    }

    private void calculate2for1Discount(int quantity) {
        if (quantity >= 2) {
            double discount = calculateCost(quantity - 1);
            System.out.println("2 for 1 Discount on DVDs: -" + discount);
        }
    }
}
