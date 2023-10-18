package day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RuckSackProblem {
    public static void main(String[] args) throws Exception {
        List<RuckSack> ruckSacks = new ArrayList<>();
        initRuckSacks(ruckSacks);
        
    }

    public static void initRuckSacks(List<RuckSack> ruckSacksToInit) throws Exception {
        List<String> ruckSackContents = readInputFromFile("day3/input.txt");
        ruckSackContents.forEach(ruckSackContent -> {
            ruckSacksToInit.add(new RuckSack(ruckSackContent));
        });
    }

    public static List<String> readInputFromFile(String filePath) throws Exception {
        Path inputPath = Path.of(filePath);
        return Files.readAllLines(inputPath);
    }
}
