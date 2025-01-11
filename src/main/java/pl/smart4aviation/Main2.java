package pl.smart4aviation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int q;

    List<SegmentTree> segmentTreeList = new ArrayList<>();

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

      SegmentTree segmentTree = new SegmentTree(array);

      for (int i = 0; i < q; i++) {
        String[] query = reader.readLine().split("\\s+");

        String operation = query[0];

        switch (operation) {
          case "Q" -> {
            int iRoute = Integer.parseInt(query[1]);
            int jRoute = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);
            int iterations = day - segmentTreeList.size();

            for (int j = 0; j < iterations; j++) {
              segmentTreeList.add(new SegmentTree(array));
            }

            int sum = 0;

            for (SegmentTree st : segmentTreeList) {
              sum += st.getSum(iRoute, jRoute);
            }

            System.out.println(sum);
          }
          case "C" -> {
            int iRoute = Integer.parseInt(query[1]);
            int day = Integer.parseInt(query[2]);

            int iterations = day - segmentTreeList.size();

            for (int j = 0; j < iterations; j++) {
              segmentTreeList.add(new SegmentTree(array));
            }

            for (SegmentTree st : segmentTreeList) {
              st.updateValue(st.getTREE(), iRoute, 0); // to jest nie tak
            }

            segmentTree.updateValue(array, iRoute, 0);
            for (SegmentTree tree : segmentTreeList) {
              System.out.println(Arrays.toString(tree.getTREE()));
            }
          }
          case "A" -> {
            int iRoute = Integer.parseInt(query[1]);
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);
            // TODO jezeli samolot jest wycofany to wtedy przypisz

            segmentTree.updateValue(array, iRoute, newPassengers);
          }
          case "P" -> {
            int iRoute = Integer.parseInt(query[1]);
            int newPassengers = Integer.parseInt(query[2]);
            int day = Integer.parseInt(query[3]);

            // TODO jezeli samolot jest wycofany to nie przypisuj
            segmentTree.updateValue(array, iRoute, newPassengers);
          }
          default -> throw new UnsupportedOperationException("No such operation");
        }
      }

    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
