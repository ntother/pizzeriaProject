package com.company;

public enum Sauce {
    GARLIC(1.0),
    MIX(1.0),
    SPICY(1.0),
    NONE(0.0);

    private double price;

    Sauce(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
