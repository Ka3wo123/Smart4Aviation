package pl.smart4aviation.factories;

public class OperationFactory {
  public static Operation getOperation(String type) {
    return switch (type) {
      case "Q" -> new QueryOperation();
      case "C" -> new CancelOperation();
      case "A" -> new AssignOperation();
      case "P" -> new UpdateOperation();
      default ->
          throw new UnsupportedOperationException(
              String.format("No such operation: %s. Select `Q`, `C`, `P` or `A`.", type));
    };
  }
}
