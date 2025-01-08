package pl.smart4aviation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int q;
    int changeDay = 0;

    try {
      String[] initParams = reader.readLine().split("\\s+");

      if (initParams.length != 2) {
        throw new ImproperNumberException(
            String.format("Expected 2 numbers ('n' and 'q') but got %d", initParams.length));
      }

      n = Integer.parseInt(initParams[0]);
      q = Integer.parseInt(initParams[1]);

      String[] maxPassengersArr = reader.readLine().split("\\s+");

      if (maxPassengersArr.length != n) {
        throw new ImproperNumberException(
            String.format(
                "Expected %d numbers of max passengers but got %d", n, maxPassengersArr.length));
      }

      int[] array = Arrays.stream(maxPassengersArr).mapToInt(Integer::parseInt).toArray();

      SegmentTree.STNode root = SegmentTree.construct(array, 0, array.length - 1);

      for (int i = 0; i < q; i++) {
        String[] query = reader.readLine().split("\\s+");

        String operation = query[0];

        switch (operation) {
          case "Q" -> {
            int iRoute = Integer.parseInt(query[1]) - 1;
            int jRoute = Integer.parseInt(query[2]) - 1;
            int day = Integer.parseInt(query[3]);
            int sum = 0;

            for(int j = 0; j < day - changeDay; j++) {
              sum += SegmentTree.getSum(root, iRoute, jRoute);
            }

            System.out.println(sum);
          }
          case "C" -> {
            int iRoute = Integer.parseInt(query[1]) - 1;
            int day = Integer.parseInt(query[2]);
            changeDay = day;
            SegmentTree.update(root, iRoute, 0);
          }
          case "A" -> {
            int iRoute = Integer.parseInt(query[1]) - 1;
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);
            changeDay = day;

            SegmentTree.update(root, iRoute, newPassengers);
          }
          case "P" -> {
            int iRoute = Integer.parseInt(query[1]) - 1;
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);
            changeDay = day;
            SegmentTree.update(root, iRoute, newPassengers);
          }
          default -> throw new UnsupportedOperationException("No such operation");
        }
      }

    } catch (IOException | UnsupportedOperationException | ImproperNumberException e) {
      System.err.println(e);
    }
  }
}
