package day7;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    Directory parent;
    String name;
    List<Directory> subDirectories = new ArrayList<>();
    Integer directStorage = 0;

    @Override
    public boolean equals(Object object) {
        if(object == null)
            return true;
        if(object instanceof Directory) {
            Directory directoryToCompare = (Directory) object;
            if(directoryToCompare.name == this.name)
                return true;
        }
        return false;
    }
}
