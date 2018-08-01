package com.company.drinks;

import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

public class Coffee implements Drink {
    private String type;
    private boolean sugar;
    private boolean milk;
    private BigDecimal price;
    private boolean size;
    private static BigDecimal milkPrice = BigDecimal.valueOf(0.50);

    public Coffee(String type,boolean size, boolean sugar, boolean milk) {
        this.type = type;
        this.sugar = sugar;
        this.milk = milk;
        this.size = size;
    }

    public static Coffee orderCoffee(String orderMenu) {
        String[][] typeArray = Utils.createOptions(orderMenu);
        System.out.println("Select coffee type: ");
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
                System.out.println("Invalid input, please select coffee type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select coffee size:");
        while (!quit) {
            System.out.println("Press 0 to select small coffee for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select large coffee for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select coffee size again");
            }
        }

        quit = false;
        boolean sugar = false;
        System.out.println("Select coffee with sugar or without:");
        while (!quit) {
            System.out.println("Press 0 to select coffee without sugar");
            System.out.println("Press 1 to select coffee with sugar");
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
                System.out.println("Invalid input, please select coffee with sugar or without again");
            }
        }


        quit = false;
        boolean milk = false;
        System.out.println("Select coffee with milk or without:");
        while (!quit) {
            System.out.println("Press 0 to select coffee without milk");
            System.out.println("Press 1 to select coffee with milk for extra: " + getMilkPrice());
            int milkValidationNumber = Utils.scannerValidator();
            if(milkValidationNumber >= 0 && milkValidationNumber <= 1) {
                switch (milkValidationNumber) {
                    case 0:
                        break;
                    case 1:
                        milk = true;
                       totalPrice = totalPrice.add(getMilkPrice());
                        break;
                }
                quit = true;
            } else {
                System.out.println("Invalid input, please select coffee with milk or without again");
            }
        }
        Coffee coffee = new Coffee(type,isBig,sugar,milk);
        coffee.setPrice(totalPrice);
        System.out.println(coffee.toString());
        return coffee;
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

    public static BigDecimal getMilkPrice() {
        return milkPrice;
    }

    public String getType() {
        return type;
    }

    public boolean isSugar() {
        return sugar;
    }

    public boolean isMilk() {
        return milk;
    }

    public boolean isSize() {
        return size;
    }

    @Override
    public String toString() {
        return (isSize() ? "Large " : "Small ") + getType() + (isMilk() ? " with milk" : " without milk") + (isSugar() ? " with sugar " : " without sugar ") + "for: " + String.format("%.2f", getPrice());
    }
}
