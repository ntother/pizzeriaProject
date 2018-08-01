package com.company.food;

import com.company.Sauce;
import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

import static com.company.Utils.createOptions;

public class Pizza implements Food {
    private String type;
    private Sauce sauce;
    private boolean isBig;
    private BigDecimal price;

    private Pizza(String type, Sauce sauce, boolean isBig) {
        this.type = type;
        this.sauce = sauce;
        this.isBig = isBig;
    }

    public static Pizza orderPizza(String orderMenu) {
        String[][] typeArray = createOptions(orderMenu);
        System.out.println("Select pizza type: ");
        boolean quit = false;
        String type = "";
        int typeNumber = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        while (!quit) {
            Utils.listTypeOption(Objects.requireNonNull(typeArray));
            typeNumber = Utils.scannerValidator();
            if (typeNumber < typeArray.length && typeNumber > -1) {
                type = typeArray[typeNumber][0];
                quit = true;
            } else {
                System.out.println("Invalid" +
                        "Invalid input, please select pizza type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select pizza size:");
        while (!quit) {
            System.out.println("Press 0 to select small pizza for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select big pizza for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
            int sizeNumber = Utils.scannerValidator();
            if (sizeNumber < typeArray[typeNumber].length - 1 && sizeNumber > -1) {
                if (sizeNumber == 0) {
                    totalPrice = BigDecimal.valueOf(Double.valueOf(typeArray[typeNumber][2]));
                } else if (sizeNumber == 1) {
                    isBig = true;
                    totalPrice = BigDecimal.valueOf(Double.valueOf(typeArray[typeNumber][1]));
                }
                quit = true;
            } else {
                System.out.println("Invalid" +
                        "Invalid input, please select pizza size again");
            }
        }
        Sauce sauce = Utils.selectSauce();

        totalPrice = BigDecimal.valueOf(sauce.getPrice()).add(totalPrice);
        Pizza pizza = new Pizza(type, sauce, isBig);
        pizza.setPrice(totalPrice);
        System.out.println(pizza.toString());
        return pizza;
    }

    private String getType() {
        return type;
    }

    private Sauce getSauce() {
        return sauce;
    }

    private boolean isSauce() {
        boolean isSauce = true;
        if (sauce == Sauce.NONE) {
            isSauce = false;
        }
        return isSauce;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        if (price.precision() >= 0) {
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return (isBig ? "Large " : "Small ") + getType().toUpperCase() + " pizza with " + (isSauce() ? getSauce() : "no") + " sauce. Price: " + String.format("%.2f", getPrice());
    }
}
