package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public record Directory(String name, List<Element> children) implements Element {

  // record para que sea inmutable (mejor)

  public Directory(String name) {
    this(name, new ArrayList<>());
  }

  @Override
  public String getName() {
    return name;
  }

  public Directory add(String path, Element component) {
    String[] parts = path.split("/");
    return addRecursive(parts, 1, component);
  }

  private Directory addRecursive(String[] parts, int index, Element component) {
    if (index >= parts.length) { // llegue al final del path
      List<Element> newChildren = new ArrayList<>(children());
      newChildren.add(component);
      return new Directory(name, newChildren);
    }

    String target = parts[index];
    List<Element> newChildren = new ArrayList<>();

    for (Element child : children) {
      switch (child) {
        case Directory dir -> {
          if (dir.getName().equals(target)) {
            Directory updated = dir.addRecursive(parts, index + 1, component);
            newChildren.add(updated);
          } else {
            newChildren.add(child);
          }
        }
        case File file -> {
          newChildren.add(file);
        }
      }
    }
    return new Directory(name, newChildren);
  }

  public Directory remove(String path, Element component) {
    String[] parts = path.split("/");
    return removeRecursive(parts, 1, component);
  }

  private Directory removeRecursive(String[] parts, int index, Element component) {
    if (index >= parts.length) {
      List<Element> newChildren = new ArrayList<>();
      for (Element child : children) {
        if (!child.getName().equals(component.getName())) {
          newChildren.add(child);
        }
      }
      return new Directory(name, newChildren);
    }

    String target = parts[index];
    List<Element> newChildren = new ArrayList<>();

    for (Element child : children) {
      switch (child) {
        case Directory dir -> {
          if (dir.getName().equals(target)) {
            Directory updated = dir.removeRecursive(parts, index + 1, component);
            newChildren.add(updated);
          }
        }
        case File file -> {
          if (!file.getName().equals(target)) {
            newChildren.add(file);
          }
        }
      }
    }
    return new Directory(name, newChildren);
  }

  public Element getChild(String name) {
    for (Element component : children) {
      if (component.getName().equals(name)) {
        return component;
      }
    }
    return null;
  }
}
