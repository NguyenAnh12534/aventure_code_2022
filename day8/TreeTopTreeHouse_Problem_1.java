import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TreeTopTreeHouse_Problem_1 {

    public static int[][] forestVisibility;
    static int counter = 0;
    public static void main(String[] args) throws Exception{
        List<String> inputLines = Files.readAllLines(Path.of("day8/input.txt"));
        forestVisibility = new int[inputLines.size()][inputLines.get(0).length()];
        scanBottomSide(inputLines);
        scanLeftSide(inputLines);
        scanRightSide(inputLines);
        scanTopSide(inputLines);
        int[][] debugArr = forestVisibility;
        System.out.println(debugArr);
        System.out.println(counter);
    }

    public static int scanTopSide(List<String> forest) {
        int forestWidth = forest.get(0).length();
        int forestHeight = forest.size();

        for(int i = 0; i < forestWidth; i++) {
            markVisible(0, i);
            int maxHeight = forest.get(0).charAt(i);
            for(int j = 1; j < forestHeight; j++) {
                int treeComparision = forest.get(j).charAt(i) - maxHeight;
                if(treeComparision > 0) {
                    markVisible(j, i);
                    maxHeight =  forest.get(j).charAt(i);
                }
            }
        }
        return counter;
    }

    public static int scanRightSide(List<String> forest) {
        int forestWidth = forest.get(0).length();
        int forestHeight = forest.size();

        for(int i = 0; i < forestHeight; i++) {
            String line = forest.get(i);
            markVisible(i, forestWidth - 1);
            int maxHeight = line.charAt(forestWidth - 1);
            for(int j = forestWidth - 2; j >= 0; j--) {
                int treeComparision = line.charAt(j) - maxHeight;
                if(treeComparision > 0) {
                    markVisible(i, j);
                    maxHeight = line.charAt(j);
                }
            }
        }
        return counter;
    }

    public static int scanBottomSide(List<String> forest) {
        int forestWidth = forest.get(0).length();
        int forestHeight = forest.size();

        for(int i = 0; i < forestWidth; i++) {
            markVisible(forestHeight - 1, i);
            int maxHeight = forest.get(forestHeight - 1).charAt(i);

            for(int j = forestHeight - 2; j >= 0; j--) {
                int treeComparision = forest.get(j).charAt(i) - maxHeight;
                if(treeComparision > 0) {
                    markVisible(j, i);
                    maxHeight = forest.get(j).charAt(i);
                }
            }
        }
        return counter;
    }

    public static int scanLeftSide(List<String> forest) {
        int forestWidth = forest.get(0).length();
        int forestHeight = forest.size();

         for(int i = 0; i < forestHeight; i++) {
            String line = forest.get(i);
            markVisible(i, 0);
            int maxHeight = line.charAt(0);
            for(int j = 1; j < forestWidth; j++) {
                int treeComparision = line.charAt(j) - maxHeight;
                if(treeComparision > 0) {
                    markVisible(i, j);
                    maxHeight = line.charAt(j);
                }
            }
        }
        return counter;
    }

    public static void markVisible(int x, int y) {
        if(forestVisibility[x][y] == 0) {
            forestVisibility[x][y] = 1;
            counter++;
        }
    }
}
