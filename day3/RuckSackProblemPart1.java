package day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class RuckSackProblemPart1 {
    public static void main(String[] args) throws Exception {
        List<String> ruckSacks = Files.readAllLines(Path.of("day3/input.txt"));
        int result = 0;
        
        for (String ruckSack : ruckSacks) {
            HashSet<Character> charSet1 = new HashSet<>();
            HashSet<Character> charSet2 = new HashSet<>();
            HashSet<Character> charSet3 = new HashSet<>();
            char[] items = ruckSack.toCharArray();
            for (int i = 0; i < items.length; i++) {
                char item = items[i];
                if (i < items.length / 3) {
                    charSet1.add(item);
                } else {
                    if (charSet1.contains(item) && !charSet2.contains(item)) {
                        charSet2.add(item);
                        if (item >= 'a') {
                            result += item - 'a' + 1;
                        } else {
                            result += item - 'A' + 27;
                        }
                    }
                }
            }
        }
        System.out.println(result);

    }

}
