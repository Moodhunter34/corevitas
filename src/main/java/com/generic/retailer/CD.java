package com.generic.retailer;

public final class CD implements Item {
    private static final int CD_COST = 10;

    @Override
    public double calculateCost(int quantity) {
        return quantity * CD_COST;
    }

    @Override
    public void displayCost() {
        System.out.println("cost of cd = " + calculateCost(1));
    }
}
