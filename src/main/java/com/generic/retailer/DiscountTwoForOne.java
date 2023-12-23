package com.generic.retailer;

public class DiscountTwoForOne implements Discount{
    public double discount = 0;
    @Override
    public double applyDiscount(double originalCost, int quantity) {
        int numberOfItems = quantity / 2 + quantity % 2;
        this.discount = numberOfItems * (originalCost / quantity);
        return numberOfItems * (originalCost / quantity);
    }


}
