package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

public class CaloriesProblem {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("day1/input.txt"));

        long maxCalories = 0;
        long currentElfCalories = 0;

        while (scanner.hasNext()) {
            String nextInput = scanner.nextLine();

            if (nextInput.isBlank()) {
                currentElfCalories = 0;
                continue;
            }

            int calory = Integer.parseInt(nextInput);
            currentElfCalories += calory;

            if (currentElfCalories > maxCalories) {
                maxCalories = currentElfCalories;
            }
        }

        System.out.println(maxCalories);
    }
}