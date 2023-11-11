package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindingStartMarker {
    public static void main(String[] agrs) throws Exception {
        int part = 1;
        FileReader fileReader = new FileReader("day6/input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int c;
        Set<Character> startMarker = new LinkedHashSet<>();
        int markerCounter = 0;
        int charCounter = 0;
        while ((c = bufferedReader.read()) != -1) {
            char character = (char) c;
            charCounter++;
            markerCounter++;

            if (startMarker.contains(character)) {
                Iterator<Character> iterator = startMarker.iterator();
                while (iterator.hasNext()) {
                    Character targetChar = iterator.next();
                    iterator.remove();
                    markerCounter--;
                    if (targetChar.equals(character)) {
                        break;
                    }
                }
            }
            startMarker.add(character);
            if (markerCounter == 14) {
                System.out.println(charCounter);
                break;
            }
        }
        bufferedReader.close();
    }
}