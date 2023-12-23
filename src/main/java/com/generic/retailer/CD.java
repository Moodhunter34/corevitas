package com.generic.retailer;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class CD implements Item {
    private static final int CD_COST = 10;

    @Override
    public double calculateCost(int quantity, LocalDate date) {
        double discount = 0;
        double cost = quantity * CD_COST;
        if (quantity % 2 == 1 && LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            discount = 0.2 * CD_COST;
            cost = cost - discount;
        }
        return cost;
    }

    @Override
    public void displayCost() {
//        System.out.println("cost of cd = " + calculateCost(1));
    }
}
