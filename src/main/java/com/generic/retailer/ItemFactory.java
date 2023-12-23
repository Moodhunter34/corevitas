package com.generic.retailer;

public class ItemFactory {

    public static Item createItem(String itemType) {
        switch (itemType) {
            case "book":
                return new Book();
            case "dvd":
                return new DVD(new DiscountTwoForOne());
            case "cd":
                return new CD();
            default:
                throw new IllegalArgumentException("Invalid item type: " + itemType);
        }
    }
}
