import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TreeHouseFinding_Problem_2 {
    public static List<String> inputLines ;
    public static void main(String[] args) throws Exception{
        inputLines = Files.readAllLines(Path.of("./day8/input_2.txt"));
        long max = 0;
        for(int row = 1; row < inputLines.size() - 1; row ++) {
            for(int column = 1; column < inputLines.get(row).length() - 1; column++) {
                long scenicScore = visibleTreesOnBottom(row, column) *
                                    visibleTreesOnLeft(row, column) *
                                    visibleTreesOnRight(row, column) *
                                    visibleTreesOnTop(row, column);
                if(scenicScore > max) {
                    max = scenicScore;
                }
            }
        }
        System.out.println(max);
    }

    public static int visibleTreesOnLeft(int row, int column) {
        int visibleTrees = 0;
        char currentTreeHeight = inputLines.get(row).charAt(column);
        for(int i = column - 1; i >= 0; i--) {
            visibleTrees++;
            if(inputLines.get(row).charAt(i) >= currentTreeHeight)
                return visibleTrees;
        }
        
        return visibleTrees;
    }

    public static int visibleTreesOnRight(int row, int column) {
        int visibleTrees = 0;
        char currentTreeHeight = inputLines.get(row).charAt(column);
        for(int i = column + 1; i < inputLines.get(row).length(); i++) {
            visibleTrees++;
            if(inputLines.get(row).charAt(i) >= currentTreeHeight)
                return visibleTrees;
        }
        
        return visibleTrees;
    }

    public static int visibleTreesOnTop(int row, int column) {
        int visibleTrees = 0;
        char currentTreeHeight = inputLines.get(row).charAt(column);
        for(int i = row - 1; i >= 0; i--) {
            visibleTrees++;
            if(inputLines.get(i).charAt(column) >= currentTreeHeight)
                return visibleTrees;
        }
        
        return visibleTrees;
    }

    public static int visibleTreesOnBottom(int row, int column) {
        int visibleTrees = 0;
        char currentTreeHeight = inputLines.get(row).charAt(column);
        for(int i = row + 1; i < inputLines.size(); i++) {
            visibleTrees++;
            if(inputLines.get(i).charAt(column) >= currentTreeHeight)
                return visibleTrees;
        }
        
        return visibleTrees;
    }
}
