package pl.smart4aviation.factory.concreteoperation;

import pl.smart4aviation.factory.Operation;
import pl.smart4aviation.model.Plane;
import pl.smart4aviation.util.SegmentTree;

import java.util.List;

public class AssignOperation implements Operation {
    @Override
    public void execute(SegmentTree segmentTree, List<Plane> planeList, String... query) {
        int iRoute = Integer.parseInt(query[1]);
        int newMaxPassengers = Integer.parseInt(query[2]);
        int day = Integer.parseInt(query[3]);

        if(!planeList.get(iRoute - 1).isActive()) {
            segmentTree.updateValue(planeList, iRoute, newMaxPassengers, day);
            planeList.get(iRoute - 1).setActive(true);
        } else {
            throw new RuntimeException("Cannot assign active plane to new route");
        }
    }
}
