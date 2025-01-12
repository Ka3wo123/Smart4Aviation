package pl.smart4aviation.factories;

import pl.smart4aviation.models.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public class CancelOperation implements Operation {
  @Override
  public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
    int iRoute = Integer.parseInt(query[1]);
    int day = Integer.parseInt(query[2]);

    segmentTree.updateValue(planeList, iRoute, 0);
  }
}
