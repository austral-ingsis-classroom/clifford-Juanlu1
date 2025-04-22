package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.System;


public class Pwd {
    private final System context;

    public Pwd(System context) {
        this.context = context;
    }

    public String execute() {
        StringBuilder path = new StringBuilder();
        Directory dir = context.getCurrentDirectory();

        while (dir != null) {
            String name = dir.getName();
            if (!name.equals("/")) {
                path.insert(0, "/" + dir.getName());
            }
            dir = dir.getParent();
        }
        if (path.isEmpty()){
            return "/";
        }
        return path.toString();
    }
}
