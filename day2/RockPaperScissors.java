package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import day2.AnswerKey.*;

public class RockPaperScissors {
    private static final String INPUT_PATH = "day2/input.txt";

    public static void main(String[] args) {
        try {
            List<String> answerSheet = readAnswerFromFilePath(INPUT_PATH);
            int totalPoint = 0;
            for (String answer : answerSheet) {
                String[] roundAnswers = answer.split("\s");
                AnswerKey elfAnswer = AnswerKey.getAnswerKeyFromChar(roundAnswers[0]);
                AnswerKey playerAnswer = AnswerKey.getAnswerKeyFromChar(roundAnswers[1]);
                System.out.println("Player answer: " + playerAnswer.toString());
                System.out.println("Elf answer: " + elfAnswer.toString());
                System.out.println("Point: " + (playerAnswer.getPoint() + playerAnswer.competeWith(elfAnswer)));

                int roundPoint = (playerAnswer.getPoint() + playerAnswer.competeWith(elfAnswer));
                totalPoint += roundPoint;
                System.out.println("Round point: " + roundPoint);
                System.out.println("Total point: " + totalPoint);
            }
            System.out.println(totalPoint);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<String> readAnswerFromFilePath(String filePath) throws IOException {
        Path pathToFile = Paths.get(filePath);
        return Files.readAllLines(pathToFile);
    }
}
