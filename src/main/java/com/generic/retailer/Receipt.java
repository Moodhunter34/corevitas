//package com.generic.retailer;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Receipt {
//
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
//    public String printTotal() {
//        return String.format("%-20s%5s%.2f", "TOTAL", "£", total);
//    }
//
//
//}