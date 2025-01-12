package pl.smart4aviation.factory.concreteoperation;

import pl.smart4aviation.factory.Operation;
import pl.smart4aviation.model.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public class QueryOperation implements Operation {
  @Override
  public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
    int iRoute = Integer.parseInt(query[1]);
    int jRoute = Integer.parseInt(query[2]);
    int day = Integer.parseInt(query[3]);

    int sum = segmentTree.getSum(iRoute, jRoute);

    sum *= day;

    System.out.println(sum);
  }
}

/*
[1,2,3,2,4]
[1,2,3,2,4]
[1,0,5,2,4]
[1,0,3,2,4]
 */
