//package com.generic.retailer;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Receipt {
//    private static double DISCOUNT_PERCENTAGE_20 = 0.8;
//    private final Map<String, Integer> items;
//
//    Trolley trolley = new Trolley();
//
//    private double total;
//
//    public Receipt() {
//        this.items = new HashMap<>();
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
//
//    public void addItem(String item, Integer quantity) {
//        items.put(item, quantity);
//    }
//
//    public String printReceipt() {
//        StringBuilder receipt = new StringBuilder();
//        System.out.println("===== RECEIPT ======");
//        for (Map.Entry<String, Integer> entry : items.entrySet()) {
//            String itemName = entry.getKey();
//            Integer itemQuantity = entry.getValue();
//            //         double productTotal = shoppingCart.calculateProductTotal(itemName, itemQuantity);
//            double productTotal = applyPercentDiscount(itemName, itemQuantity);
//            receipt.append(String.format("%-20s%10s%n", itemName.toUpperCase(), "£" + String.format("%.2f", productTotal)));
//        }
//        receipt.append(String.format("====================%n"));
//        return receipt + printTotal();
//    }
//
//    // 20% off on Thursdays
//    private double discountThursdays(String itemName, LocalDate currentDay) {
//        double itemPrice = trolley.getProductPrice(itemName);
//        if (currentDay.getDayOfWeek() == DayOfWeek.THURSDAY) {
//            return itemPrice * DISCOUNT_PERCENTAGE_20;
//        }
//        return itemPrice;
//    }
//
//    private double applyPercentDiscount(String itemName, int itemQuantity) {
//        // Retrieve  price for current item
//        double itemPrice = trolley.getProductPrice(itemName);
//
//        // Apply discounts based on item type
//        if ("dvd".equals(itemName) && itemQuantity >= 2) {
//            // 2 for 1 on DVDs
//            int numberOfPairs = itemQuantity / 2;
//            if (itemQuantity % 2 == 0) {
//                return (numberOfPairs * itemPrice);
//            } else {
//                return (numberOfPairs * itemPrice) + itemPrice;
//            }
//        }
//
//        // No discount, regular price
//        return (itemPrice * itemQuantity);
//    }
//
//    public String printTotal() {
//        return String.format("%-20s%5s%.2f", "TOTAL", "£", total);
//    }
//
//
//}