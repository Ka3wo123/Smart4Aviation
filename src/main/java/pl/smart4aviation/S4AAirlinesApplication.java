package pl.smart4aviation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import pl.smart4aviation.exceptions.ImproperNumberException;
import pl.smart4aviation.models.Plane;
import pl.smart4aviation.utils.SegmentTree;

public class S4AAirlinesApplication {
  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int q;

    try {
      System.out.println("Provide `n` and `q`");
      String[] initParams = reader.readLine().split("\\s+");

      if (initParams.length != 2) {
        throw new ImproperNumberException(
            String.format("Expected 2 numbers ('n' and 'q') but got %d", initParams.length));
      }

      n = Integer.parseInt(initParams[0]);
      q = Integer.parseInt(initParams[1]);

      System.out.printf("Provide %d numbers of max passengers%n", n);
      String[] maxPassengersArr = reader.readLine().split("\\s+");

      if (maxPassengersArr.length != n) {
        throw new ImproperNumberException(
            String.format(
                "Expected %d numbers of max passengers but got %d", n, maxPassengersArr.length));
      }

      List<Plane> planeList =
          Arrays.stream(maxPassengersArr)
              .map(maxPassengers -> new Plane(Integer.parseInt(maxPassengers)))
              .toList();

      SegmentTree segmentTree = new SegmentTree(planeList);

      for (int i = 0; i < q; i++) {
        System.out.println("Pick operation `Q`, `P`, `C`, `A`");
        String[] query = reader.readLine().split("\\s+");

        String operation = query[0];

        switch (operation) {
          case "Q" -> {
            int iRoute = Integer.parseInt(query[1]);
            int jRoute = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);

            int sum = segmentTree.getSum(iRoute, jRoute);
            System.out.println(sum);
          }
          case "C" -> {
            int iRoute = Integer.parseInt(query[1]);
            int day = Integer.parseInt(query[2]);

            segmentTree.updateValue(planeList, iRoute, 0);
          }
          case "A" -> {
            int iRoute = Integer.parseInt(query[1]);
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);
            // TODO jezeli samolot jest wycofany to wtedy przypisz

            segmentTree.updateValue(planeList, iRoute, newPassengers);
          }
          case "P" -> {
            int iRoute = Integer.parseInt(query[1]);
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);

            // TODO jezeli samolot jest wycofany to nie przypisuj
            segmentTree.updateValue(planeList, iRoute, newPassengers);
          }
          default -> throw new UnsupportedOperationException("No such operation");
        }
      }

    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
