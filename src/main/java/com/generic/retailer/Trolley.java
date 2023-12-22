package com.generic.retailer;


import java.util.HashMap;
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
}