package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.System;


public class Pwd {
    private final System context;

    public Pwd(System context) {
        this.context = context;
    }

    public System execute() {
        return new System(context.currentPath(), context.root(),  context.currentPath());
    }
}
