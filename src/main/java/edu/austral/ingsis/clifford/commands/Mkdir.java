package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.System;

public class Mkdir {
    private final System context;
    private final String directoryName;

    public Mkdir(System context, String directoryName) {
        this.context = context;
        this.directoryName = directoryName;
    }

    public String execute() {
        if (directoryName.contains("/") || directoryName.contains(" ")) {
            return "Invalid directory name";
        }
        Directory currentDir = context.getCurrentDirectory();
        Directory directory = new Directory(directoryName, currentDir.getRoot());
        currentDir.add(directory);
        return "'" + directoryName + "' directory created";
    }
}
