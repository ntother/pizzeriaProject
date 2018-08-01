package com.company.drinks;

import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

public class Juice implements Drink {
    private static BigDecimal naturalJuiceExtraPrice = BigDecimal.valueOf(1.0);
    private String type;
    private boolean isNatural;
    private boolean size;
    private BigDecimal price;

    public Juice(String type, boolean size, boolean isNatural) {
        this.type = type;
        this.isNatural = isNatural;
        this.size = size;
    }

    public static BigDecimal getNaturalJuiceExtraPrice() {
        return naturalJuiceExtraPrice;
    }

    public static Juice orderJuice(String orderMenu) {
        String[][] typeArray = Utils.createOptions(orderMenu);
        System.out.println("Select juice type: ");
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
                System.out.println("Invalid input, please select juice type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select juice size:");
        while (!quit) {
            System.out.println("Press 0 to select small juice for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select large juice for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select juice size again");
            }
        }

        quit = false;
        boolean isNatural = false;
        System.out.println("Select natural juice or not:");
        while (!quit) {
            System.out.println("Press 0 to select common juice");
            System.out.println("Press 1 to select natural juice for extra: " + getNaturalJuiceExtraPrice());
            int milkValidationNumber = Utils.scannerValidator();
            if (milkValidationNumber >= 0 && milkValidationNumber <= 1) {
                switch (milkValidationNumber) {
                    case 0:
                        break;
                    case 1:
                        isNatural = true;
                        totalPrice = totalPrice.add(getNaturalJuiceExtraPrice());
                        break;
                }
                quit = true;
            } else {
                System.out.println("Invalid input, please select natural juice or not again");
            }
        }
        Juice juice = new Juice(type, isBig, isNatural);
        juice.setPrice(totalPrice);
        System.out.println(juice.toString());
        return juice;
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

    @Override
    public String toString() {
        return (isSize() ? "Large " : "Small ") + getType() + (isNatural() ? " natural juice " : " juice ") + "for: " + String.format("%.2f", getPrice());

    }

    public String getType() {
        return type;
    }

    public boolean isNatural() {
        return isNatural;
    }

    public boolean isSize() {
        return size;
    }
}
