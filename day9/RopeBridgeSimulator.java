package day9;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RopeBridgeSimulator {
    public static Map<Character, Vector2D> commandToMovementMap = Map.of(
        'R', new Vector2D(1, 0),
        'U', new Vector2D(0, 1),
        'L', new Vector2D(-1, 0),
        'D', new Vector2D(0, -1)
        );


    public static void main(String[] args) throws Exception {
        String part1InputFile = "./day9/data/part1.txt";
        String part2InputFile = "./day9/data/part2.txt";
        solvePart1(part1InputFile);
        solvePart2(part2InputFile);
    }

    public static void solvePart1(String inputPath) throws Exception {
        List<String> commands = Files.readAllLines(Path.of(inputPath));
        Vector2D tail = new Vector2D(0, 0);
        Vector2D head = new Vector2D(0, 0);
        Set<Vector2D> tailVistedPositions = new HashSet<>();
        tailVistedPositions.add(tail.createClone());
        for(String command : commands) {
            Vector2D movement = commandToMovementMap.get(command.charAt(0));
            int moveTimes = Integer.parseInt(command.substring(2));
            while(moveTimes > 0) {
                head.add(movement);
                if(!tail.isAdjacentTo(head)) {
                    tail.follow(head);
                    tailVistedPositions.add(tail.createClone());
                }
                moveTimes--;
            }
        }

        System.out.println(tailVistedPositions.size());

    }

    public static void solvePart2(String inputPath) throws Exception {
        List<String> commands = Files.readAllLines(Path.of(inputPath));
        List<Vector2D> ropeNodes = List.of(
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0)
        );
        Vector2D head = ropeNodes.get(0);
        head.attachTo(head);
        Vector2D tail =ropeNodes.get(9);

        for(int i = 1; i < ropeNodes.size(); i++) {
            ropeNodes.get(i).attachTo(ropeNodes.get(i-1));
        }

        Set<Vector2D> tailVistedPositions = new HashSet<>();
        tailVistedPositions.add(tail.createClone());
        for(String command : commands) {
            Vector2D movement = commandToMovementMap.get(command.charAt(0));
            int moveTimes = Integer.parseInt(command.substring(2));
            while(moveTimes > 0) {
                head.add(movement);
                ropeNodes.forEach(ropeNode -> {
                    ropeNode.simulateRopeMovement();
                });
                tailVistedPositions.add(tail.createClone());
                moveTimes--;
            }
        }

        System.out.println(tailVistedPositions.size());
    }

}
