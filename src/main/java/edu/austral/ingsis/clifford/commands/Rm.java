package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.System;

public class Rm {
    private final System context;
    private String name;

    public Rm(System context, String name) {
        this.context = context;
        this.name = name;
    }

    public String execute(){
        Directory currentDir = context.getCurrentDirectory();
        if (name.contains("recursive")){
           name = name.substring("--recursive ".length()).trim();
           FileSystem dir = currentDir.getChild(name);
           if (dir == null) {
               return "No such file or directory";
           }
           return removeRecursive((Directory) dir);
        }
        FileSystem file = currentDir.getChild(name);
        if (file == null) {
            return "No such file or directory";
        }
        else if (file.isDirectory()) {
            return "cannot remove '" + file.getName() + "', is a directory";
        } else {
            currentDir.remove(file);
            return "'" + name + "' removed";
        }
    }

    private String removeRecursive(Directory directory) {
        for (FileSystem child : directory.list()) {
            if (child.isDirectory()) {
                removeRecursive((Directory) child);
            }
            directory.remove(child);
        }
        directory.getParent().remove(directory);
        return "'" + directory.getName() + "' removed";
    }
}
