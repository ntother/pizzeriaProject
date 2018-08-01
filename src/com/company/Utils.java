package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Utils {
    public static String[][] createOptions(String sourceFile) {
        try {
            Stream<String> lines = Files.lines(Paths.get(sourceFile));
            List<String> stringList = lines.collect(Collectors.toList());
            String[][] typeArray = new String[stringList.size()][3];
            int orderTypeNumber = 0;
            for (String element : stringList) {
                String[] list = element.split("(:\\s|,\\s*)");
                for (int i = 0; i < 1; i++) {
                    typeArray[orderTypeNumber][i] = list[1];
                    typeArray[orderTypeNumber][i + 1] = list[3];
                    typeArray[orderTypeNumber][i + 2] = list[5];
                    ++orderTypeNumber;
                }
            }
            return typeArray;
        } catch (IOException e) {

        }
        return null;
    }

    public static int scannerValidator() {
        Scanner scanner = new Scanner(System.in);
        try {
            String scanned = scanner.nextLine();
            return Integer.valueOf(scanned);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void listTypeOption(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Press " + i + " to select: " + array[i][0]);
        }
    }

    public static Sauce selectSauce() {
        System.out.println("Select sauce type:");
        Sauce sauce = Sauce.NONE;
        boolean quit = false;
        while (!quit) {
            System.out.println("Press 0 to select Garlic sauce for " + String.format("%.2f", Sauce.GARLIC.getPrice()) +
                    "\nPress 1 to select Mix sauce for " + String.format("%.2f", Sauce.MIX.getPrice()) +
                    "\nPress 2 to select Spicy sauce for " + String.format("%.2f", Sauce.SPICY.getPrice()) +
                    "\nPress 3 to select without sauce");
            int sauceTypeNumber = Utils.scannerValidator();
            if (sauceTypeNumber >= 0 && sauceTypeNumber <= 3) {
                switch (sauceTypeNumber) {
                    case 0:
                        sauce = Sauce.GARLIC;
                        break;
                    case 1:
                        sauce = Sauce.MIX;
                        break;
                    case 2:
                        sauce = Sauce.SPICY;
                        break;
                    case 3:
                        break;
                }
                quit = true;
            } else {
                System.out.println("Invalid input, please select sauce again");
            }
        }
        return sauce;
    }

    public static void getCheque(List<MenuItem> order) throws IOException {
        File file = new File("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/cheque/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY_MM_dd_HH-mm-ss")) + "cheque" + ".txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter("/home/ntother/Desktop/Programming/BalticTalents/Java/Teamwork/src/com/company/cheque/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY_MM_dd_HH-mm-ss")) + "cheque" + ".txt");
        Iterator<MenuItem> iterator = order.iterator();
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int i = 0;
        bufferedWriter.append("Cheque print time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))).append("\n");
        bufferedWriter.append("Order list:\n");
        while (iterator.hasNext()) {
            bufferedWriter.append(String.valueOf(i)).append(". ").append(iterator.next().toString()).append("\n");
            i++;
        }
        BigDecimal sumWithoutTax = BigDecimal.valueOf(0);
        BigDecimal taxValue = BigDecimal.valueOf(1.21);
        iterator = order.iterator();
        while (iterator.hasNext()) {
            sumWithoutTax = iterator.next().getPrice().add(sumWithoutTax);
        }
        BigDecimal sumWithTax = sumWithoutTax.multiply(taxValue);
        BigDecimal taxSum = sumWithTax.subtract(sumWithoutTax);
        bufferedWriter.append("\n\t\t\t\t\t\t\t\t\t\tPrice without tax: ").append(String.format("%.2f", sumWithoutTax));
        bufferedWriter.append("\n\t\t\t\t\t\t\t\t\t\tTax amount 21%: ").append(String.format("%.2f", taxSum));
        bufferedWriter.append("\n\t\t\t\t\t\t\t\t\t\tTotal price with tax: ").append(String.format("%.2f", sumWithTax));
        bufferedWriter.append("\n\n Approximate order completion time: ").append(LocalDateTime.now().plus(20, MINUTES).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))).append("\n");

        bufferedWriter.close();
    }
}
