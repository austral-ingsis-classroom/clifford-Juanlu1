package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.System;
import edu.austral.ingsis.clifford.File;

public class Touch {
    private final System context;
    private final String fileName;

    public Touch(System context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public String execute() {
        if (fileName.contains("/") || fileName.contains(" ")) {
            return "Invalid file name";
        }
        File file = new File(fileName);
        context.getCurrentDirectory().add(file);
        return "'" + fileName + "' file created";
    }
}
