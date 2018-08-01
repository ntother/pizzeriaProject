package com.company.drinks;

import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

public class Tea implements Drink {
    private String type;
    private boolean sugar;
    private boolean size;
    private BigDecimal price;

    public Tea(String type, boolean sugar, boolean size) {
        this.type = type;
        this.sugar = sugar;
        this.size = size;
    }

    public static Tea orderTea(String orderMenu) {
        String[][] typeArray = Utils.createOptions(orderMenu);
        System.out.println("Select tea type: ");
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
                System.out.println("Invalid input, please select tea type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select coffee size:");
        while (!quit) {
            System.out.println("Press 0 to select small tea for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select large tea for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select tea size again");
            }
        }

        quit = false;
        boolean sugar = false;
        System.out.println("Tea coffee with sugar or without:");
        while (!quit) {
            System.out.println("Press 0 to select tea without sugar");
            System.out.println("Press 1 to select tea with sugar");
            int sugarValidationNumber = Utils.scannerValidator();
            if(sugarValidationNumber >= 0 && sugarValidationNumber <= 1) {
                switch (sugarValidationNumber) {
                    case 0:
                        break;
                    case 1:
                        sugar = true;
                        break;
                }
                quit = true;
            } else {
                System.out.println("Invalid input, please select tea with sugar or without again");
            }
        }
        Tea tea = new Tea(type,isBig,sugar);
        tea.setPrice(totalPrice);
        System.out.println(tea.toString());
        return tea;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        if (price.precision() >= 0) {
            this.price = price;
        }
    }

    public String getType() {
        return type;
    }

    public boolean isSugar() {
        return sugar;
    }

    public boolean isSize() {
        return size;
    }

    @Override
    public String toString() {
        return (isSize() ? "Large " : "Small ") + getType() + " tea " + (isSugar() ? " with sugar " : " without sugar ") + "for: " + String.format("%.2f", getPrice());
    }
}
