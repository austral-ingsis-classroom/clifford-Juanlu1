package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.System;
import edu.austral.ingsis.clifford.Element;

import java.util.Comparator;
import java.util.List;

public class Ls {
    private final System context;
    private final String order;

    public Ls(System context, String order) {
        this.context = context;
        this.order = order;
    }

    public System execute() {
        List<Element> elements = context.getCurrentDirectory().children();

        Comparator<Element> comparator = Comparator.comparing(Element::getName);
        if (order.contains("desc")) {
            comparator = comparator.reversed();
        }
        if (order.contains("asc") || order.contains("desc")) {
            elements.sort(comparator);
        }

        StringBuilder result = new StringBuilder();
        for (Element element : elements) {
            result.append(element.getName()).append(" ");
        }
        if (!result.isEmpty()) {
            result.setLength(result.length() - 1);
        }
        return new System(context.currentPath(), context.root(), result.toString());
    }
}
