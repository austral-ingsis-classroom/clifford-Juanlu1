package edu.austral.ingsis.clifford;

public record File(String name) implements Element {

  // record para que sea inmutable (mejor)

  @Override
  public String getName() {
    return name;
  }
}
