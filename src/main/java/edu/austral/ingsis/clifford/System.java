package edu.austral.ingsis.clifford;

public record System(String currentPath, Directory root, String message) {

    //currentDir() es el getter
    public System(String currentPath, Directory root) {
        this(currentPath, root, "");
    }

    public Directory getCurrentDirectory() {
        if (currentPath.equals("/")) {
            return root;
        }
        String[] parts = currentPath.split("/");
        Directory currentDir = root;

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            currentDir = (Directory) currentDir.getChild(part);
        }
        return currentDir;
    }
}
