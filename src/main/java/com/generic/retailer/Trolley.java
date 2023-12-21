package com.generic.retailer;

import java.util.HashMap;
import java.util.Map;

public final class Trolley {

    private final Book book = new Book();
    private final CD cd = new CD();
    private final DVD dvd = new DVD();

    private Map<String, Integer> order;

    public Trolley() {
        this.order = new HashMap<>();
    }

    public void ordersProducts(String product, int quantity) {
        order.put(product, quantity);
    }

    public double calculateTotal() {
        double total = 0.0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            total += calculateProductTotal(product, quantity);
        }
        return total;
    }

    public double getProductPrice(String product){
        double price = 0.0;

        switch (product) {
            case "book":
                price = book.getPrice();
                break;
            case "cd":
                price = cd.getPrice();
                break;
            case "dvd":
                price = dvd.getPrice();
                break;
            default:
                System.out.println("Invalid product: " + product);
        }
        return price;
    }

    public double calculateProductTotal(String product, int quantity) {

        return getProductPrice(product) * quantity;
    }

}
