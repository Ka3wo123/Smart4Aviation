package pl.smart4aviation.factory;

import pl.smart4aviation.factory.concreteoperation.AssignOperation;
import pl.smart4aviation.factory.concreteoperation.CancelOperation;
import pl.smart4aviation.factory.concreteoperation.QueryOperation;
import pl.smart4aviation.factory.concreteoperation.UpdateOperation;

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
