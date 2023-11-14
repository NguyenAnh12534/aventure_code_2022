package day7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CleanupDevice {
    static final Integer TOTAL_STORAGE = 70000000;
    static final Integer NEEDED_SPACE = 30000000;
    static Directory rootDirectory = new Directory();
    static Directory currentDirectory = rootDirectory;
    static List<Directory> resultDirs = new ArrayList<>();

    public static void main(String[] agrs) throws Exception {
        resultDirs.add(rootDirectory);
        rootDirectory.parent = rootDirectory;
        List<String> inputLines = Files.readAllLines(Path.of("day7/input.txt"));
        rootDirectory.name = "";
        for (String inputLine : inputLines) {
            if (inputLine.startsWith("$")) {
                // Command to execute
                String[] tokens = inputLine.split(" ");
                String command = tokens[1];
                if (command.equals("cd")) {
                    currentDirectory = getCurrentDirectory(tokens[2]);
                }
            } else if (inputLine.startsWith("dir")) {
                // it is a directory => skip
               
            } else {
                Integer fileSize = Integer.parseInt(inputLine.split(" ")[0]);
                currentDirectory.directStorage += fileSize;
                if (currentDirectory.directStorage > 100000)
                    resultDirs.remove(currentDirectory);
                Directory tempDir = currentDirectory;
                do {
                    if (tempDir.parent == tempDir)
                        break;
                    tempDir = tempDir.parent;

                    tempDir.directStorage += fileSize;
                    if (tempDir.directStorage > 100000)
                        resultDirs.remove(tempDir);
                } while (!tempDir.equals(rootDirectory));
            }
        }

        int spaceLeft = TOTAL_STORAGE - rootDirectory.directStorage;
        int spaceToFree = NEEDED_SPACE - spaceLeft;

        // Part 1
        Integer result = resultDirs.stream().map(dir -> {
            return dir.directStorage;
        }).reduce(0, (storage1, storage2) -> {
            return storage1 + storage2;
        });
        System.out.println(result);

        // Part 2
        System.out.println(findDirectoryToDelete(rootDirectory, spaceToFree).directStorage);
        System.out.println(rootDirectory.directStorage);
    }

    public static Directory findDirectoryToDelete(Directory rooDirectory, int spaceNeedToFree) {
        if(rooDirectory == null) {
            return null;
        }

        Stack<Directory> dirStack = new Stack<>();
        dirStack.push(rooDirectory);
        Directory deleteCandidate = rooDirectory;
        while(!dirStack.isEmpty()) {
            Directory tempDir = dirStack.pop();
            if(tempDir.directStorage < spaceNeedToFree) {
                continue;
            } else {
                if(tempDir.directStorage < deleteCandidate.directStorage) {
                    deleteCandidate = tempDir;
                }
            }

            for(Directory directory : tempDir.subDirectories) {
                dirStack.push(directory);
            }
        }

        return deleteCandidate;
    }

    public static Directory getCurrentDirectory(String dirPath) {
        if (dirPath.equals(".."))
            return currentDirectory.parent;

        if (!dirPath.contains("/")) {
            for (Directory directory : currentDirectory.subDirectories) {
                if (directory.name.equals(dirPath)) {
                    return directory;
                }
            }
            Directory newDirectory = new Directory();
            newDirectory.name = dirPath;
            currentDirectory.subDirectories.add(newDirectory);
            newDirectory.parent = currentDirectory;
            resultDirs.add(newDirectory);
            return newDirectory;
        }

        if (dirPath.equals("/"))
            return rootDirectory;

        return null;
    }
}