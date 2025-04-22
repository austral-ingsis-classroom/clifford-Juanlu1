package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
    private final Directory root;
    private String name;
    private Directory parent;
    private List<FileSystem> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
        this.root = this; //chequear
    }
    public Directory(String name, Directory root) {
        this.name = name;
        this.root = root;
    }

    @Override
    public String getName() { return name; }

    @Override
    public Directory getParent() { return parent; }

    @Override
    public void setParent(Directory parent) { this.parent = parent; }

    @Override
    public boolean isDirectory() { return true; }

    @Override
    public void add(FileSystem component) {
        component.setParent(this);
        children.add(component);
    }

    @Override
    public void remove(FileSystem component) {
        children.remove(component);
    }

    @Override
    public FileSystem getChild(String name) {
        for (FileSystem component : children) {
            if (component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }

    @Override
    public List<FileSystem> list() {
        return new ArrayList<>(children);
    }

    public Directory getRoot() {
        return root;
    }
}
