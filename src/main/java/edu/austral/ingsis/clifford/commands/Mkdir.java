package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.System;

public class Mkdir {
  private final System context;
  private final String directoryName;

  public Mkdir(System context, String directoryName) {
    this.context = context;
    this.directoryName = directoryName;
  }

  public System execute() {
    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return new System(context.currentPath(), context.root(), "Invalid directory name");
    }
    Directory root = context.root();
    Directory newDir = new Directory(directoryName);

    Directory updatedDir = root.add(context.currentPath(), newDir);
    return new System(
        context.currentPath(), updatedDir, "'" + directoryName + "' directory created");
  }
}
