package pl.smart4aviation.factories;

import pl.smart4aviation.factories.concreteoperations.AssignOperation;
import pl.smart4aviation.factories.concreteoperations.CancelOperation;
import pl.smart4aviation.factories.concreteoperations.QueryOperation;
import pl.smart4aviation.factories.concreteoperations.UpdateOperation;

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
