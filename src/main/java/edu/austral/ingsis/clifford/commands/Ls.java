package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.System;
import edu.austral.ingsis.clifford.FileSystem;

import java.util.Comparator;
import java.util.List;

public class Ls {
    private final System context;
    private final String order;

    public Ls(System context, String order) {
        this.context = context;
        this.order = order;
    }

    public String execute() {
        List<FileSystem> elements = context.getCurrentDirectory().list();

        Comparator<FileSystem> comparator = Comparator.comparing(FileSystem::getName);
        if (order.contains("desc")) {
            comparator = comparator.reversed();
        }
        if (order.contains("asc") || order.contains("desc")) {
            elements.sort(comparator);
        }

        StringBuilder result = new StringBuilder();
        for (FileSystem element : elements) {
            result.append(element.getName()).append(" ");
        }
        if (!result.isEmpty()) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }
}
