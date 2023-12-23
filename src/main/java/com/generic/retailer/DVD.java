package com.generic.retailer;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DVD implements Item {
    private static final double DVD_COST = 15;
    private final Discount discount;

    public double discount2for1 = 0;
    public DVD(Discount discount) {
        this.discount = discount;
    }

    public static double getDvdCost() {
        return DVD_COST;
    }

    public double getDiscount2for1() {
        return discount2for1;
    }

    //    public Discount getDiscount() {
//        return discount;
//    }
    //    @Override
//    public double calculateCost(int quantity) {
//        int numberOfDVDs = quantity / 2 + quantity % 2;
//        double dvdCost = numberOfDVDs * DVD_COST;
//
//        if (quantity % 2 == 1 && LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
//            dvdCost -= 0.2 * DVD_COST;
//        }
//
//        return dvdCost;
//    }

    @Override
    public double calculateCost(int quantity) {
        double originalCost = quantity * DVD_COST;
        double discountedCost = discount.applyDiscount(originalCost, quantity);

        this.discount2for1 = discountedCost;
        return discountedCost;
    }

    @Override
    public void displayCost() {
//        System.out.println("cost of dvd = " + calculateCost(1));
    }
}
