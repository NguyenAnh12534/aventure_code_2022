package day5;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class CrartesRearrangement {
    public static void main(String[] agrs) throws Exception {
        int crateMoverModel = 9001;
        final int CRATE_INDENTIFIER_LENGTH = 3;
        boolean isReadingCratesPosition = true;
        List<String> cratePositions = new ArrayList<>();
        int numberOfCrateSections = 0;
        Scanner scanner = new Scanner(Path.of("day5/input.txt"));

        while (isReadingCratesPosition) {
            String inputLine = scanner.nextLine();
            if (inputLine.contains("[")) {
                cratePositions.add(inputLine);
            } else {
                String[] positions = inputLine.trim().split("\\s+");
                numberOfCrateSections = positions.length;
                isReadingCratesPosition = false;
                scanner.nextLine();
            }
        }
        if (numberOfCrateSections == 0) {
            return;
        } else {
            // Reading crate stacks
            List<Stack<String>> crateStacks = new ArrayList<>();
            for (int i = 0; i < numberOfCrateSections; i++) {
                crateStacks.add(new Stack<String>());
            }

            for (int index = cratePositions.size() - 1; index >= 0; index--) {
                int nextCrateStartIndex = 0;
                for (int i = 1; i <= numberOfCrateSections; i++) {
                    int endIndex = nextCrateStartIndex + CRATE_INDENTIFIER_LENGTH;
                    String crate;
                    if (i > 1) {
                        endIndex++;
                    }
                    crate = cratePositions.get(index).substring(nextCrateStartIndex,
                            endIndex).trim();
                    nextCrateStartIndex = endIndex;
                    Stack<String> crateStack = crateStacks.get(i - 1);
                    if (!crate.isBlank()) {
                        crateStack.push(crate.substring(1, 2));
                    }
                }
            }
            // Performing crate rearrangment
            while (scanner.hasNext()) {
                // Read command
                String command = scanner.nextLine();
                String[] commandKeys = command.split("\\D+");
                int numberOfCrateToMove = Integer.parseInt(commandKeys[1]);
                int sourceCrateStackIndex = Integer.parseInt(commandKeys[2]);
                int targetCrateStackIndex = Integer.parseInt(commandKeys[3]);
                Stack<String> sourceStack = crateStacks.get(sourceCrateStackIndex - 1);
                Stack<String> targetStack = crateStacks.get(targetCrateStackIndex - 1);
                switch (crateMoverModel) {
                    // part 1
                    case 9000:
                        // Execute Command
                        while (numberOfCrateToMove > 0) {
                            targetStack.push(sourceStack.pop());
                            numberOfCrateToMove--;
                        }
                        break;
                    // part 2
                    case 9001:
                        Stack<String> crateStack = new Stack<>();
                        while (numberOfCrateToMove > 0) {
                            crateStack.add(sourceStack.pop());
                            numberOfCrateToMove--;
                        }
                        while (!crateStack.isEmpty()) {
                            targetStack.push(crateStack.pop());
                        }
                        break;
                    default:
                        break;
                }

            }

            for (int i = 0; i < numberOfCrateSections; i++) {
                System.out.print(crateStacks.get(i).peek());
            }
        }
    }
}
