package edu.austral.ingsis.clifford;

import java.util.List;

public interface FileSystem {
    String getName();
    Directory getParent();
    void setParent(Directory parent);
    boolean isDirectory();

    void add(FileSystem component);
    void remove(FileSystem component);
    FileSystem getChild(String name);
    List<FileSystem> list();
}
