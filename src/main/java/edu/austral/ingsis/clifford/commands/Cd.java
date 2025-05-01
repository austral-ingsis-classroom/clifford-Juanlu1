package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.System;

public class Cd {
  private final System context;
  private final String path;

  public Cd(System context, String path) {
    this.context = context;
    this.path = path;
  }

  public System execute() {
    Directory currentDir = context.getCurrentDirectory();
    String currentPath = context.currentPath();

    if (path.equals(".")) {
      return new System(
          currentPath, context.root(), "moved to directory '" + currentDir.getName() + "'");
    } else if (path.equals("..")) {
      Directory parentDir = findParent(context.root());
      if (parentDir != null) {

        String parentPath = getParentPath();

        return new System(
            parentPath, context.root(), "moved to directory '" + parentDir.getName() + "'");
      } else {
        return new System(
            currentPath, context.root(), "moved to directory '" + currentDir.getName() + "'");
      }
    } else {
      String[] parts = path.split("/");
      Directory first;

      if (path.startsWith("/")) {
        first = context.root();
      } else {
        first = currentDir;
      }

      String newPath = first.getName();
      for (String part : parts) {
        if (part.isEmpty() || part.equals(".")) continue;

        var child = first.getChild(part);
        if (child == null) {
          return new System(currentPath, context.root(), "'" + part + "' directory does not exist");
        }
        switch (child) {
          case Directory dir -> {
            first = dir;
            if (newPath.equals("/")) {
              newPath += first.getName();
            } else {
              newPath += "/" + first.getName();
            }
          }
          case File file -> {
            return new System(
                currentPath, context.root(), "'" + part + "' directory does not exist");
          }
        }
      }
      return new System(newPath, context.root(), "moved to directory '" + first.getName() + "'");
    }
  }

  private String getParentPath() {
    int lastSlash = context.currentPath().lastIndexOf('/');
    String parentPath;

    if (lastSlash == 0) {
      parentPath = "/";
    } else {
      parentPath = path.substring(0, lastSlash);
    }
    return parentPath;
  }

  private Directory findParent(Directory root) {
    String path = context.currentPath();
    if (path.equals("/")) {
      return context.root();
    }
    String parentPath = path.substring(0, path.lastIndexOf('/'));

    if (parentPath.equals("/") || parentPath.isEmpty()) {
      return root;
    }

    String[] parts = parentPath.split("/");
    Directory currentDir = root;

    for (String part : parts) {
      currentDir = (Directory) currentDir.getChild(part);
    }
    return currentDir;
  }
}
