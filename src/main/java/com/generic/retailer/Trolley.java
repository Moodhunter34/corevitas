package com.generic.retailer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public double calculateCost() {
        double costs = 0;
        for (Map.Entry<String, Integer> entry : this.order.entrySet()) {
            String itemType = entry.getKey();
            Integer quantity = entry.getValue();

            Item currentItem = ItemFactory.createItem(itemType);
            costs += currentItem.calculateCost(quantity);
            currentItem.displayCost();
        }
        return costs;
    }

    public void printReceipt() {
        List<String> receiptLines = new ArrayList<>();
        double totalDiscount = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemType = entry.getKey();
            Integer quantity = entry.getValue();

            Item currentItem = ItemFactory.createItem(itemType);
            totalDiscount -= currentItem.calculateCost(quantity - 1);
            receiptLines.add(String.format("%-14s £%-7.2f", itemType.toUpperCase(), currentItem.calculateCost(quantity)));
        }

        receiptLines.add("====================");
        receiptLines.add(String.format("%-14s £%-7.2f", "TOTAL", calculateCost()));
        receiptLines.add(String.format("%-14s £%-7.2f", "DISCOUNT", totalDiscount));
        receiptLines.add("====================");

        System.out.println("===== RECEIPT ======");
        receiptLines.forEach(System.out::println);
    }
}