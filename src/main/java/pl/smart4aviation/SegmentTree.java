package pl.smart4aviation;

public class SegmentTree {

  public static class STNode {
    private int leftIndex;
    private int rightIndex;
    private int sum;
    private STNode leftNode;
    private STNode rightNode;
  }

  public static STNode construct(int[] arr, int start, int end) {
    if (start == end) {
      STNode node = new STNode();
      node.leftIndex = start;
      node.rightIndex = end;
      node.sum = arr[start];
      return node;
    }

    int mid = (start + end) / 2;
    STNode leftNode = construct(arr, start, mid);
    STNode rightNode = construct(arr, mid + 1, end);
    STNode parent = new STNode();
    parent.leftIndex = leftNode.leftIndex;
    parent.rightIndex = rightNode.rightIndex;
    parent.sum = leftNode.sum + rightNode.sum;
    parent.leftNode = leftNode;
    parent.rightNode = rightNode;
    return parent;
  }

  public static int getSum(STNode parent, int leftIdx, int rightIdx) {
    if (parent.leftIndex >= leftIdx && parent.rightIndex <= rightIdx) {
      return parent.sum;
    }

    if (parent.rightIndex < leftIdx || parent.leftIndex > rightIdx) {
      return 0;
    }

    return getSum(parent.leftNode, leftIdx, rightIdx) + getSum(parent.rightNode, leftIdx, rightIdx);
  }

  public static int update(STNode parent, int index, int value) {
    int diff;
    if (parent.leftIndex == parent.rightIndex && index == parent.leftIndex) {
      diff = value - parent.sum;
      parent.sum = value;
      return diff;
    }

    int mid = (parent.leftIndex + parent.rightIndex) / 2;
    if (index <= mid) {
      diff = update(parent.leftNode, index, value);
    } else {
      diff = update(parent.rightNode, index, value);
    }

    parent.sum += diff;
    return diff;
  }
}
