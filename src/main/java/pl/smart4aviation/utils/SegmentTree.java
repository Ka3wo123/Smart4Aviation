package pl.smart4aviation.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.smart4aviation.model.Plane;

public class SegmentTree {
  private final List<Plane> TREE;
  private final int START_INDEX = 0;
  private final int END_INDEX;
  private final int CURRENT_INDEX = 0;

  public SegmentTree(List<Plane> array) {
    int size = array.size();
    int maxSize = 2 * size - 1;
    TREE = new ArrayList<>(Collections.nCopies(maxSize, new Plane(0)));
    END_INDEX = size - 1;
    constructTreeUtil(array, START_INDEX, END_INDEX, CURRENT_INDEX);
  }

  public int getSum(int queryStart, int queryEnd) {
    return getSumUtil(START_INDEX, END_INDEX, queryStart - 1, queryEnd - 1, CURRENT_INDEX);
  }

  public void updateValue(List<Plane> list, int updateIndex, int newValue) {
    Plane planeToUpdate = list.get(updateIndex - 1);
    int diff = newValue - planeToUpdate.getMaxPassengers();

    planeToUpdate.setMaxPassengers(newValue);

    updateValueUtil(START_INDEX, END_INDEX, updateIndex - 1, diff, CURRENT_INDEX);
    list.get(updateIndex - 1).setMaxPassengers(newValue);
  }

  private void updateValueUtil(
      int startIndex, int endIndex, int updateIndex, int diff, int currentIndex) {
    if (updateIndex < startIndex || updateIndex > endIndex) return;

    int maxPassengers = TREE.get(currentIndex).getMaxPassengers();
    TREE.get(currentIndex).setMaxPassengers(maxPassengers + diff);

    if (startIndex != endIndex) {
      int mid = getMid(startIndex, endIndex);
      updateValueUtil(startIndex, mid, updateIndex, diff, 2 * currentIndex + 1);
      updateValueUtil(mid + 1, endIndex, updateIndex, diff, 2 * currentIndex + 2);
    }
  }

  private int getSumUtil(
      int startIndex, int endIndex, int queryStart, int queryEnd, int currentIndex) {
    Plane plane = TREE.get(currentIndex);
    if (queryStart <= startIndex && queryEnd >= endIndex && plane.isActive()) {
      return plane.getMaxPassengers();
    }

    if (endIndex < queryStart || startIndex > queryEnd || !plane.isActive()) {
      return 0;
    }

    int mid = getMid(startIndex, endIndex);

    return getSumUtil(startIndex, mid, queryStart, queryEnd, 2 * currentIndex + 1)
        + getSumUtil(mid + 1, endIndex, queryStart, queryEnd, 2 * currentIndex + 2);
  }

  private int constructTreeUtil(List<Plane> list, int startIndex, int endIndex, int currentIndex) {
    if (startIndex == endIndex) {
      Plane plane = list.get(startIndex);
      TREE.set(currentIndex, plane);
      return plane.getMaxPassengers();
    }

    int mid = getMid(startIndex, endIndex);
    Plane parent = new Plane(0);
    int leftSum = constructTreeUtil(list, startIndex, mid, 2 * currentIndex + 1);
    int rightSum = constructTreeUtil(list, mid + 1, endIndex, 2 * currentIndex + 2);

    parent.setMaxPassengers(leftSum + rightSum);
    TREE.set(currentIndex, parent);

    return parent.getMaxPassengers();
  }

  private int getMid(int startIndex, int endIndex) {
    return startIndex + (endIndex - startIndex) / 2;
  }
}
