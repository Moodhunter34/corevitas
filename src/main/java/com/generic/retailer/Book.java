package com.generic.retailer;

public final class Book implements Item {

    private static final int BOOK_COST = 5;

    @Override
    public double calculateCost(int quantity) {
        return quantity * BOOK_COST;
    }

    @Override
    public void displayCost() {
        System.out.println("cost of book = " + calculateCost(1));
    }
    
}
