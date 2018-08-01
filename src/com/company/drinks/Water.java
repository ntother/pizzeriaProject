package com.company.drinks;

import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

public class Water implements Drink {
    private String type;
    private boolean size;
    private BigDecimal price;
    private static BigDecimal sparklingWaterExtraPrice = BigDecimal.valueOf(0.30);

    public Water(String type,boolean size) {
        this.type = type;
        this.size = size;
    }

    public static Water orderWater(String orderMenu) {
        String[][] typeArray = Utils.createOptions(orderMenu);
        System.out.println("Select water type: ");
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
                System.out.println("Invalid input, please select water type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select water size:");
        while (!quit) {
            System.out.println("Press 0 to select small water for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select large water for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select water size again");
            }
        }

        Water water = new Water(type, isBig);
        water.setPrice(totalPrice);
        System.out.println(water.toString());
        return water;
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

    public boolean isSize() {
        return size;
    }

    @Override
    public String toString() {
        return (isSize() ? "Large " : "Small ") + getType() + " water " + "for: " + String.format("%.2f", getPrice());

    }

}
