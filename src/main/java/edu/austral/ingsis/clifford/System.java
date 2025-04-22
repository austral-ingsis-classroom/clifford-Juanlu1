package edu.austral.ingsis.clifford;

public class System {

    private Directory currentDir;

    public System(Directory currentDir) {
        this.currentDir = currentDir;
    }

    public Directory getCurrentDirectory() {
        return currentDir;
    }

    public void setCurrentDirectory(Directory newDirectory) {
        this.currentDir = newDirectory;
    }
}
