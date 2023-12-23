package com.generic.retailer;


import java.time.LocalDate;
import java.util.*;

public final class Trolley {

    private final Map<String, Integer> order;

    public Trolley() {
        this.order = new HashMap<>();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public void addToTrolley(String item, int quantity) {
        order.put(item, order.getOrDefault(item, 0) + quantity);
    }

    public double calculateCost(LocalDate date) {
        double costs = 0;
        for (Map.Entry<String, Integer> entry : this.order.entrySet()) {
            String itemType = entry.getKey();
            Integer quantity = entry.getValue();

            Item currentItem = ItemFactory.createItem(itemType);
            costs += currentItem.calculateCost(quantity, date);
            currentItem.displayCost();
        }
        return costs;
    }
    public void printReceipt(LocalDate date) {
        List<String> receiptLines = new ArrayList<>();
        double totalDiscount = 0;
        double dvdDiscount = 0;
        double thurDiscount = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemType = entry.getKey();
            Integer quantity = entry.getValue();

            Item currentItem = ItemFactory.createItem(itemType);

            if (currentItem instanceof DVD) {
                totalDiscount -= currentItem.calculateCost(quantity - 1, date);
                dvdDiscount = ((DVD) currentItem).getDiscount2for1();
                thurDiscount = ((DVD) currentItem).getDiscount2for1();
            }

            if (quantity > 1) {
                receiptLines.add(String.format("%-13s £%-10.2f", itemType.toUpperCase() + " (x" + quantity + ")", currentItem.calculateCost(quantity, date)));
            } else {
                receiptLines.add(String.format("%-13s £%-10.2f", itemType.toUpperCase(), currentItem.calculateCost(quantity, date)));
            }
        }

        if (order.containsKey("dvd") && order.get("dvd") > 1) {
            receiptLines.add(String.format("%-13s £%-10.2f", "2 FOR 1", dvdDiscount));
        }

        receiptLines.add("====================");
        receiptLines.add(String.format("%-13s £%-10.2f", "TOTAL", calculateCost(date)));
        // receiptLines.add(String.format("%-22s £%-10.2f", "DISCOUNT", totalDiscount));
        receiptLines.add("====================");

        System.out.println("===== RECEIPT ======");
        receiptLines.forEach(System.out::println);
    }

//    public void printReceipt(LocalDate date) {
//        List<String> receiptLines = new ArrayList<>();
//        double totalDiscount = 0;
//        double dvdDiscount = 0;
//
//        for (Map.Entry<String, Integer> entry : order.entrySet()) {
//            String itemType = entry.getKey();
//            Integer quantity = entry.getValue();
//
//            Item currentItem = ItemFactory.createItem(itemType);
//
//            if (currentItem instanceof DVD) {
//                totalDiscount -= currentItem.calculateCost(quantity - 1,date);
//                dvdDiscount = ((DVD) currentItem).getDiscount2for1();
//            }
//
//            if (quantity > 1){
//                receiptLines.add(String.format("%-2s  (x%d) £%-7.2f", itemType.toUpperCase(), quantity, currentItem.calculateCost(quantity, date)));
//
//            } else {
//                receiptLines.add(String.format("%-14s £%-7.2f", itemType.toUpperCase(), currentItem.calculateCost(quantity, date)));
//            }
//        }
//
//        if (order.containsKey("dvd") && order.get("dvd") > 1) {
//            receiptLines.add(String.format("2 FOR 1 £%7.2f",dvdDiscount));
//
//        }
//        receiptLines.add("====================");
//        receiptLines.add(String.format("%-14s £%-7.2f", "TOTAL", calculateCost(date)));
////        receiptLines.add(String.format("%-14s £%-7.2f", "DISCOUNT", totalDiscount));
//        receiptLines.add("====================");
//
//        System.out.println("===== RECEIPT ======");
//        receiptLines.forEach(System.out::println);
//    }
}