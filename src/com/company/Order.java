package com.company;

import com.company.drinks.*;
import com.company.food.Kebab;
import com.company.food.Pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Order {
    static void makeOrder() {
        boolean quit = false;
        System.out.println("Make order:");
        List<MenuItem> order = new ArrayList<>();
        while (!quit) {
            System.out.println("Press 0 to order food" +
                    "\nPress 1 to order drinks" +
                    "\nPress 2 to show check" +
                    "\nPress 3 to get cheque" +
                    "\nPress 4 to reset order" +
                    "\nPress 5 to quit");
            int menuOrderOption = Utils.scannerValidator();
            if (menuOrderOption >= 0 && menuOrderOption <= 4) {
                switch (menuOrderOption) {
                    case 0:
                        boolean quitFood = false;
                        while (!quitFood) {
                            System.out.println("Press 0 to select pizza" +
                                    "\nPress 1 to select kebab");
                            int foodNumber = Utils.scannerValidator();
                            if (foodNumber >= 0 && foodNumber <= 1) {
                                switch (foodNumber) {
                                    case 0:
                                        order.add(Pizza.orderPizza("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/pizzaMenu.txt"));
                                        quitFood = true;
                                        break;
                                    case 1:
                                        order.add(Kebab.orderKebab("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/kebabMenu.txt"));
                                        quitFood = true;
                                        break;
                                }
                            } else {
                                System.out.println("Invalid input, please select type of food again");
                            }
                        }
                        break;
                    case 1:
                        boolean quitDrink = false;
                        while (!quitDrink) {
                            System.out.println("Press 0 to select beer" +
                                    "\nPress 1 to select coffee" +
                                    "\nPress 2 to select tea" +
                                    "\nPress 3 to select juice");
                            int drinkNumber = Utils.scannerValidator();
                            if (drinkNumber >= 0 && drinkNumber <= 2) {
                                switch (drinkNumber) {
                                    case 0:
                                        order.add(Beer.orderBeer("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/beerMenu.txt"));
                                        quitDrink = true;
                                        break;
                                    case 1:
                                        order.add(Coffee.orderCoffee("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/coffeeMenu.txt"));
                                        quitDrink = true;
                                        break;
                                    case 2:
                                        order.add(Tea.orderTea("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/teaMenu.txt"));
                                        quitDrink = true;
                                        break;
                                    case 3:
                                        order.add(Juice.orderJuice("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/juiceMenu.txt"));
                                        quitDrink = true;
                                        break;
                                    case 4:
                                        order.add(Water.orderWater("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/menuFiles/waterMenu.txt"));
                                        break;
                                }
                            }
                        }
                        break;
                    case 2:
                        if (!order.isEmpty()) {
                            order.forEach(System.out::println);
                        } else {
                            System.out.println("You haven't ordered anything yet");
                        }
                        break;
                    case 3:
                        if (!order.isEmpty()) {
                            System.out.println("The order: ");
                            order.forEach(System.out::println);
                            boolean confirmation = false;
                            while (!confirmation) {
                                System.out.println("Press 0 to go back to the menu" +
                                        "\nPress 1 to confirm the order");
                                int verification = Utils.scannerValidator();
                                if (verification >= 0 && verification <= 1) {
                                    switch (verification) {
                                        case 0:
                                            confirmation = true;
                                            break;
                                        case 1:
                                            try {
                                                Utils.getCheque(order);
                                                confirmation = true;
                                                quit = true;
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                    }
                                }
                            }
                            break;
                        } else {
                            System.out.println("You haven't ordered anything yet");
                            break;
                        }
                    case 4:
                        if (order.isEmpty()) {
                            System.out.println("You haven't ordered anything yet");
                            break;
                        } else {
                            order.clear();
                            break;
                        }
                    case 5:
                        System.out.println("Have a nice day!" +
                                "\nGoodbye!");
                        quit = true;
                        break;
                }
            } else {
                System.out.println("Invalid input, please select menu option again");
            }
        }
    }
}
