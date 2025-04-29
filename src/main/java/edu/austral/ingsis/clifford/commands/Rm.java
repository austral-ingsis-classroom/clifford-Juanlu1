package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.Element;
import edu.austral.ingsis.clifford.System;

public class Rm {
    private final System context;
    private String name;

    public Rm(System context, String name) {
        this.context = context;
        this.name = name;
    }

    public System execute() {
        boolean recursive = false;

        if (name.contains("recursive")) {
            recursive = true;
            name = name.substring("--recursive ".length()).trim();
        }

        Element file = context.getCurrentDirectory().getChild(name);
        Directory root = context.root();

        if (file == null) {
            return new System(context.currentPath(), root, "No such file or directory");
        }
        if (file.isDirectory() && !recursive) {
            return new System(context.currentPath(), root, "cannot remove '" + file.getName() + "', is a directory");
        }

        Directory updatedDir = root.remove(context.currentPath(), file);
        return new System(context.currentPath(), updatedDir, "'" + name + "' removed");
    }
}
