package pl.smart4aviation;

import lombok.Getter;

public class SegmentTree {
  @Getter private int[] TREE;
  private final int START_INDEX = 0;
  private final int END_INDEX;
  private final int CURRENT_INDEX = 0;

  public SegmentTree(int[] array) {
    int size = array.length;
    int maxSize = 2 * size - 1;
    TREE = new int[maxSize];
    END_INDEX = size - 1;
    constructTreeUtil(array, START_INDEX, END_INDEX, CURRENT_INDEX);
  }

  public int getSum(int queryStart, int queryEnd) {
    return getSumUtil(START_INDEX, END_INDEX, queryStart - 1, queryEnd - 1, CURRENT_INDEX);
  }

  public void updateValue(int[] array, int updateIndex, int newValue) {
    int diff = newValue - array[updateIndex - 1];
    if (newValue == 0) {
      array[updateIndex - 1] = 0;
    } else {
      array[updateIndex - 1] = diff;
    }

    updateValueUtil(START_INDEX, END_INDEX, updateIndex - 1, diff, CURRENT_INDEX);
  }

  private void updateValueUtil(
      int startIndex, int endIndex, int updateIndex, int diff, int currentIndex) {
    if (updateIndex < startIndex || updateIndex > endIndex) return;
    TREE[currentIndex] = TREE[currentIndex] + diff;
    if (startIndex != endIndex) {
      int mid = getMid(startIndex, endIndex);
      updateValueUtil(startIndex, mid, updateIndex, diff, 2 * currentIndex + 1);
      updateValueUtil(mid + 1, endIndex, updateIndex, diff, 2 * currentIndex + 2);
    }
  }

  private int getSumUtil(
      int startIndex, int endIndex, int queryStart, int queryEnd, int currentIndex) {
    if (queryStart <= startIndex && queryEnd >= endIndex) {
      return TREE[currentIndex];
    }

    if (endIndex < queryStart || startIndex > queryEnd) {
      return 0;
    }

    int mid = getMid(startIndex, endIndex);

    return getSumUtil(startIndex, mid, queryStart, queryEnd, 2 * currentIndex + 1)
        + getSumUtil(mid + 1, endIndex, queryStart, queryEnd, 2 * currentIndex + 2);
  }

  private int constructTreeUtil(int[] array, int startIndex, int endIndex, int currentIndex) {
    if (startIndex == endIndex) {
      TREE[currentIndex] = array[startIndex];
      return array[startIndex];
    }

    int mid = getMid(startIndex, endIndex);
    TREE[currentIndex] =
        constructTreeUtil(array, startIndex, mid, 2 * currentIndex + 1)
            + constructTreeUtil(array, mid + 1, endIndex, 2 * currentIndex + 2);
    return TREE[currentIndex];
  }

  private int getMid(int startIndex, int endIndex) {
    return startIndex + (endIndex - startIndex) / 2;
  }
}
