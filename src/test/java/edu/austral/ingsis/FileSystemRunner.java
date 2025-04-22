package edu.austral.ingsis;

import edu.austral.ingsis.clifford.Directory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FileSystemRunner {
  private final Map<String, Function<String, String>> commandMap;
  public FileSystemRunner(Map<String, Function<String, String>> commandMap) {
    this.commandMap = commandMap;
  }
  public String executeCommand(String input) {
    String[] parts = input.split(" ", 2);
    String commandName = parts[0];
    String text = parts.length > 1 ? parts[1] : "";

    //obtiene la funcion del comando y lo ejecuta (lo que le doy y lo que devuelve tiene)

    Function<String, String> command = commandMap.get(commandName);
    if (command != null) {
      return command.apply(text); //le aplica el texto
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
