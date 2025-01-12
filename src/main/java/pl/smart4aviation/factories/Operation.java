package pl.smart4aviation.factories;

import pl.smart4aviation.models.Plane;
import pl.smart4aviation.utils.SegmentTree;

import java.util.List;

public interface Operation {
    void execute(SegmentTree segmentTree, List<Plane> planeList, String... query);
}
