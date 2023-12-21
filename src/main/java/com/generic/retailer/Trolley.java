package com.generic.retailer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class Trolley {

    private Map<String, Integer> order;

    public Trolley() {
        this.order = new HashMap<>();
        this.order.put("books", 0);
        this.order.put("cd", 0);
        this.order.put("dvd", 0);
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int addToTrolley() {
        return 1;
    }

    public double calculateCost() {
        double costs = 0;
        for (Map.Entry<String, Integer> entry : this.order.entrySet()) {
            String key = entry.getKey();
            Integer quantity = entry.getValue();

            if (key == "books") {
                int book_cost = quantity * 5;
                costs = costs + book_cost;
                System.out.println("cost of books=" + book_cost);

            } else if (key == "dvd") {
                int number_of_dvs = quantity / 2 + quantity % 2;
                double dvd_cost = (double) number_of_dvs * 15;
                if (quantity % 2 == 1 && LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
                    dvd_cost = dvd_cost - 0.2 * 15;

                }
                costs = costs + dvd_cost;

                System.out.println("cost of dvd=" + dvd_cost);
            } else if (key == "cd") {
                int cd_cost = quantity * 10;

                costs = costs + cd_cost;
                System.out.println("cost of cd=" + cd_cost);
            }
        }
        return costs;
    }
}