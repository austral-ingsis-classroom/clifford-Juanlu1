package edu.austral.ingsis.clifford;

import java.util.List;

public class File implements FileSystem {
    private String name;
    private Directory parent;

    public File(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }

    @Override
    public Directory getParent() { return parent; }

    @Override
    public void setParent(Directory parent) { this.parent = parent; }

    @Override
    public boolean isDirectory() { return false; }

    @Override
    public void add(FileSystem component) {
        throw new IllegalArgumentException("Files cant have children.");
    }

    @Override
    public void remove(FileSystem component) {
        throw new IllegalArgumentException("Files dont have children.");
    }

    @Override
    public FileSystem getChild(String name) {
        throw new IllegalArgumentException("Files dont have children.");
    }

    @Override
    public List<FileSystem> list() {
        throw new IllegalArgumentException("Files dont have children.");
    }
}
