package pl.smart4aviation.runner;

import pl.smart4aviation.exception.ImproperNumberException;
import pl.smart4aviation.factory.Operation;
import pl.smart4aviation.factory.OperationFactory;
import pl.smart4aviation.model.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Runner {
  public static void run() {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int q;

    try {
      System.out.println("Provide `n` and `q`");
      String[] initParams = reader.readLine().split("\\s+");

      checkGivenInput(initParams, 2);

      n = Integer.parseInt(initParams[0]);
      q = Integer.parseInt(initParams[1]);

      System.out.printf("Provide %d numbers of max passengers%n", n);
      String[] maxPassengersArr = reader.readLine().split("\\s+");

      checkGivenInput(maxPassengersArr, n);

      List<Plane> planeList =
          Arrays.stream(maxPassengersArr)
              .map(maxPassengers -> new Plane(Integer.parseInt(maxPassengers)))
              .toList();

      SegmentTree segmentTree = new SegmentTree(planeList);

      for (int i = 0; i < q; i++) {
        String[] query = reader.readLine().split("\\s+");

        String operationType = query[0];
        Operation operation = OperationFactory.getOperation(operationType);
        operation.execute(segmentTree, planeList, query);
      }

    } catch (Exception e) {
      System.err.println(e);
    }
  }

  private static void checkGivenInput(Object[] arr, int expectedLength) {
    if (arr.length != expectedLength) {
      throw new ImproperNumberException(
          String.format("Expected %d numbers but got %d", expectedLength, arr.length));
    }
  }
}
