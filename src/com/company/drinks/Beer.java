package com.company.drinks;

import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

public class Beer implements Drink {
    private String type;
    private BigDecimal price;
    private boolean isBig;

    public Beer(String type, boolean isBig) {
        this.type = type;
        this.isBig = isBig;
    }

    public static Beer orderBeer(String orderMenu) {
        String[][] typeArray = Utils.createOptions(orderMenu);
        System.out.println("Select beer type: ");
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
                System.out.println("Invalid input, please select beer type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select beer size:");
        while (!quit) {
            System.out.println("Press 0 to select small beer for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select large beer for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select beer size again");
            }
        }
        Beer beer = new Beer(type, isBig);
        beer.setPrice(totalPrice);
        System.out.println(beer.toString());
        return beer;
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

    public boolean isBig() {
        return isBig;
    }

    @Override
    public String toString() {
        return (isBig() ? "Large " : "Small ") + getType() + " beer. Price: " + String.format("%.2f", getPrice());

    }
}
