package edu.austral.ingsis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import edu.austral.ingsis.clifford.System;

public class FileSystemRunner {
  private System context;
  private final Map<String, BiFunction<System, String, System>> commandMap;
  public FileSystemRunner(System context, Map<String, BiFunction<System, String, System>> commandMap) {
    this.context = context;
    this.commandMap = commandMap;
  }
  public String executeCommand(String input) {
    String[] parts = input.split(" ", 2);
    String commandName = parts[0];
    String text = parts.length > 1 ? parts[1] : "";

    //obtiene la funcion del comando y lo ejecuta (lo que le doy y lo que devuelve tiene)

    BiFunction<System, String, System> command = commandMap.get(commandName);
    if (command != null) {
      context = command.apply(context, text);
      return context.message();
    } else {
      return "Command not found: " + commandName;
    }
  }

  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String command : commands) {
      results.add(executeCommand(command));
    }
    return results;
  }
}
