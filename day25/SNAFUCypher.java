package day25;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

public class SNAFUCypher {
    public static void main(String[] args) throws Exception {
        System.out.println(convertNumberToSnafu(120l));
        List<String> snafuCodes = Files.readAllLines(Path.of("day25/input.txt"));
        long totalFuel = 0;

        for(String snafuCode : snafuCodes) {
            totalFuel += convertSnafuToNumber(snafuCode);
        }

        System.out.println(convertNumberToSnafu(totalFuel));
        // List<String> snafuCodes = Files.readAllLines(Path.of("day25/input.txt"));
        // long totalFuel = 0;
        // for (String snafuCode : snafuCodes) {
        // totalFuel += convertSnafuToNumber(snafuCode);
        // }

        // System.out.println(totalFuel);
    }

    public static String convertNumberToSnafu(Long fuelNumber) {
        Stack<Character> snafuChars = new Stack<>();
        int leftOver = 0;
        do{
            int remaining = (int) (fuelNumber % 5);
        
            if(remaining > 2) {
                remaining = remaining - 5;
                leftOver = 1;
            }else {
                leftOver = 0;
            }
            switch(remaining) {
                case -1 -> snafuChars.push('-');
                case -2 -> snafuChars.push('=');
                default -> {
                    snafuChars.push((char) (remaining + '0'));
                }
            }

            fuelNumber = fuelNumber / 5;
            fuelNumber += leftOver;
        }
        while(fuelNumber != 0 );

        StringBuilder snafuCodeBuilder = new StringBuilder();
        

        while(!snafuChars.isEmpty()) {
    
            snafuCodeBuilder.append(snafuChars.pop());
        }

        return snafuCodeBuilder.toString();
    }

    public static long convertSnafuToNumber(String snafuCode) {
        Stack<Integer> snafuChars = new Stack<>();
        long resultNumber = 0;
        for (int i = 0; i < snafuCode.length(); i++) {
            int snafuInNumberFormat;
            int snafuIndex = snafuCode.length() - 1 - i;
            switch (snafuCode.charAt(i)) {
                case '-' -> {
                    snafuInNumberFormat = -1;
                }
                case '=' -> {
                    snafuInNumberFormat = -2;
                }
                default -> {
                    snafuInNumberFormat = snafuCode.charAt(i) - '0';

                }
            }

            resultNumber += snafuInNumberFormat * Math.pow(5, snafuIndex);
        }

        return resultNumber;
    }
}
