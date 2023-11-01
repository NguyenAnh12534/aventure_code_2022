package day4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CampCleanup {
    public static void main(String[] args) throws Exception {
        List<String> elvePairs = Files.readAllLines(Path.of("day4/input.txt"));
        int result = 0;
        int part = 2;
        for (String elvePair : elvePairs) {
            String[] elveSections = elvePair.split(",");
            String firstElveSection = elveSections[0];
            Integer firstElveLowerBound = Integer.parseInt(firstElveSection.split("-")[0]);
            Integer firstElveUpperBound = Integer.parseInt(firstElveSection.split("-")[1]);

            String secondElveSection = elveSections[1];
            Integer secondElveLowerBound = Integer.parseInt(secondElveSection.split("-")[0]);
            Integer secondElveUpperBound = Integer.parseInt(secondElveSection.split("-")[1]);
            int lowerBoundComparision;
            int upperBoundComparision;
            switch (part) {
                case 1:
                    lowerBoundComparision = firstElveLowerBound - secondElveLowerBound;
                    upperBoundComparision = firstElveUpperBound - secondElveUpperBound;
                    if (lowerBoundComparision * upperBoundComparision <= 0) {
                        result++;
                    }
                    break;
                case 2:
                    lowerBoundComparision = firstElveUpperBound - secondElveLowerBound;
                    upperBoundComparision = firstElveLowerBound - secondElveUpperBound;
                    if (lowerBoundComparision >=0 && upperBoundComparision <= 0) {
                        result++;
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println(result);
    }
}
