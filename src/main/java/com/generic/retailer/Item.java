package com.generic.retailer;

import java.time.LocalDate;

interface Item {

    double calculateCost(int quantity, LocalDate date);
    void displayCost();
}
