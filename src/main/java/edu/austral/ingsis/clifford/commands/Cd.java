package edu.austral.ingsis.clifford.commands;


import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.System;

public class Cd {
    private final System context;
    private final String path;

    public Cd(System context, String path) {
        this.context = context;
        this.path = path;
    }

    public String execute() {
        Directory currentDir = context.getCurrentDirectory();

        if (path.equals(".")) {

            return "moved to directory '" + currentDir.getName() + "'";
        }
        else if (path.equals("..")) {
            if (currentDir.getParent() != null) {
                context.setCurrentDirectory(currentDir.getParent());
                currentDir = context.getCurrentDirectory();
                return "moved to directory '" + currentDir.getName() + "'";
            } else {
                return "moved to directory '" + currentDir.getName() + "'";
            }
        }
        else{
            String[] parts = path.split("/");
            Directory first;

            if (path.startsWith("/")) {
                first = currentDir.getRoot();
            }
            else{
                first = currentDir;
            }

            for (String part : parts) {
                if (part.isEmpty() || part.equals(".")) continue;

                var child = first.getChild(part);
                if (child == null || !child.isDirectory()) {
                    return "'" + part + "' directory does not exist";
                }
                first = (Directory) child;
            }

            context.setCurrentDirectory(first);
            currentDir = context.getCurrentDirectory();
            return "moved to directory '" + currentDir.getName() + "'";
        }
    }


}
