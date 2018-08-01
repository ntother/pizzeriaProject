package com.company.food;

import com.company.Sauce;
import com.company.Utils;

import java.math.BigDecimal;
import java.util.Objects;

import static com.company.Utils.createOptions;

public class Kebab implements Food {
    private String meatType;
    private boolean big;
    private Sauce sauce;
    private BigDecimal price;

    public Kebab(String meatType,Sauce sauce, boolean big) {
        this.big = big;
        this.meatType = meatType;
        this.sauce = sauce;
    }

    public static Kebab orderKebab(String orderMenu) {
        String[][] typeArray = createOptions(orderMenu);
        System.out.println("Select kebab type: ");
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
                System.out.println("Invalid input, please select kebab type again");
            }
        }
        quit = false;
        boolean isBig = false;
        System.out.println("Select kebab size:");
        while (!quit) {
            System.out.println("Press 0 to select small kebab for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][2])));
            System.out.println("Press 1 to select big kebab for " + String.format("%.2f", Double.valueOf(typeArray[typeNumber][1])));
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
                System.out.println("Invalid input, please select pizza size again");
            }
        }
        Sauce sauce = Utils.selectSauce();

        totalPrice = BigDecimal.valueOf(sauce.getPrice()).add(totalPrice);
        Kebab kebab = new Kebab(type, sauce, isBig);
        kebab.setPrice(totalPrice);
        System.out.println(kebab.toString());
        return kebab;
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

    public String getMeatType() {
        return meatType;
    }

    public boolean isBig() {
        return big;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public boolean hasKebabSauce() {
        boolean isKebabSauce = true;
        if (sauce == Sauce.NONE) {
            isKebabSauce = false;
        }
        return isKebabSauce;

    }

    @Override
    public String toString() {
        return (isBig() ? "Large " : "Small ") + getMeatType() + " kebab with " + (hasKebabSauce() ? getSauce() : "no ") + " sauce. Total price: " + String.format("%.2f", getPrice());
    }

}
