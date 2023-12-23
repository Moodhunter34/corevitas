package com.generic.retailer;

import java.time.DayOfWeek;
import java.time.LocalDate;

public final class Book implements Item {

    private static final int BOOK_COST = 5;

    @Override
    public double calculateCost(int quantity, LocalDate date) {
        double discount = 0;
        double cost = quantity * BOOK_COST;
        if (quantity % 2 == 1 && LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            discount = 0.2 * BOOK_COST;
            cost = cost - discount;
        }
        return cost;
    }

    @Override
    public void displayCost() {
//        System.out.println("cost of book = " + calculateCost(1));
    }
    
}
