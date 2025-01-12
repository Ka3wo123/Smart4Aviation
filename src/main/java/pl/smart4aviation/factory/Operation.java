package pl.smart4aviation.factory;

import pl.smart4aviation.model.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public interface Operation {
    void execute(SegmentTree segmentTree, List<Plane> planeList, String... query);
}
