package day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class RuckSackProbliemPart2 {
    public static void main(String[] args) throws Exception {
        List<String> ruckSacks = Files.readAllLines(Path.of("day3/input.txt"));
        int result = 0;
        int groupCounter = 0;
        HashSet<Character> firstCompartmentItems = new HashSet<>();
        HashSet<Character> secondCompartmentItems = new HashSet<>();
        HashSet<Character> thirdCompartmentItems = new HashSet<>();

        for (String ruckSack : ruckSacks) {
            if (groupCounter > 2) {
                
                groupCounter = 0;
                firstCompartmentItems.clear();
                secondCompartmentItems.clear();
                thirdCompartmentItems.clear();
            }

                for (int i = 0; i < ruckSack.length(); i++) {
                    Character item = ruckSack.charAt(i);
                    if (groupCounter == 0) {
                        // First compartment items
                        firstCompartmentItems.add(item);
                    } else if (groupCounter == 1) {
                        // Second compartment items
                        if (firstCompartmentItems.contains(item)) {
                            secondCompartmentItems.add(item);
                        }
                    } else {
                        // Third compartment items
                        if (firstCompartmentItems.contains(item) && secondCompartmentItems.contains(item) && !thirdCompartmentItems.contains(item)) {
                            thirdCompartmentItems.add(item);
                            if (item >= 'a') {
                                result += item - 'a' + 1;
                            } else {
                                result += item - 'A' + 27;
                            }
                        }
                    }
                }
                groupCounter++;
        }
        System.out.println(result);
    }
}
