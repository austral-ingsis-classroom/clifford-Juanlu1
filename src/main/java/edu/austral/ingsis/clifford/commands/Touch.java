package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.System;

public class Touch {
  private final System context;
  private final String fileName;

  public Touch(System context, String fileName) {
    this.context = context;
    this.fileName = fileName;
  }

  public System execute() {
    if (fileName.contains("/") || fileName.contains(" ")) {
      return new System(context.currentPath(), context.root(), "Invalid file name");
    }
    Directory root = context.root();
    File newfile = new File(fileName);

    Directory updatedDir = root.add(context.currentPath(), newfile);
    return new System(context.currentPath(), updatedDir, "'" + fileName + "' file created");
  }
}
