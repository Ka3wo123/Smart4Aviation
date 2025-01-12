package pl.smart4aviation.factory.concreteoperation;

import pl.smart4aviation.factory.Operation;
import pl.smart4aviation.model.Plane;
import pl.smart4aviation.util.SegmentTree;

import java.util.List;

public class CancelOperation implements Operation {
  @Override
  public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
    int iRoute = Integer.parseInt(query[1]);
    int day = Integer.parseInt(query[2]);

    segmentTree.updateValue(planeList, iRoute, 0);
    planeList.get(iRoute - 1).setActive(false);
  }
}
