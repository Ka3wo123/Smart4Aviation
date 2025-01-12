package pl.smart4aviation.factories.concreteoperations;

import pl.smart4aviation.factories.Operation;
import pl.smart4aviation.models.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public class UpdateOperation implements Operation {
  @Override
  public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
    int iRoute = Integer.parseInt(query[1]);
    int newMaxPassengers = Integer.parseInt(query[2]);
    int day = Integer.parseInt(query[3]);

    if(planeList.get(iRoute - 1).isActive()) {
        segmentTree.updateValue(planeList, iRoute, newMaxPassengers);
    } else {
        throw new RuntimeException("Cannot update max passengers number because this plane is inactive");
    }
  }
}
