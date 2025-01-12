package pl.smart4aviation.factories.concreteoperations;

import pl.smart4aviation.factories.Operation;
import pl.smart4aviation.models.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public class QueryOperation implements Operation {
  @Override
  public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
    int iRoute = Integer.parseInt(query[1]);
    int jRoute = Integer.parseInt(query[2]);
    int day = Integer.parseInt(query[3]);

    System.out.println(planeList);
    int sum = segmentTree.getSum(iRoute, jRoute);

    System.out.println(sum);
  }
}
