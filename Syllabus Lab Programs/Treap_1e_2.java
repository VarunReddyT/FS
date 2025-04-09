import java.util.*;

public class Treap_1e_2 {
  static class TreapNode {
    long key;
    int priority, size;
    TreapNode left, right;

    TreapNode(long key) {
      this.key = key;
      this.priority = new Random().nextInt();
      this.size = 1;
    }
  }

  static class Treap {
    TreapNode root;

    int size(TreapNode node) {
      return node == null ? 0 : node.size;
    }

    void updateSize(TreapNode node) {
      if (node != null) {
        node.size = 1 + size(node.left) + size(node.right);
      }
    }

    TreapNode rotateRight(TreapNode y) {
      TreapNode x = y.left;
      TreapNode T2 = x.right;
      x.right = y;
      y.left = T2;
      updateSize(y);
      updateSize(x);
      return x;
    }

    TreapNode rotateLeft(TreapNode x) {
      TreapNode y = x.right;
      TreapNode T2 = y.left;
      y.left = x;
      x.right = T2;
      updateSize(x);
      updateSize(y);
      return y;
    }

    TreapNode insert(TreapNode node, long key) {
      if (node == null)
        return new TreapNode(key);
      if (key <= node.key) {
        node.left = insert(node.left, key);
        if (node.left.priority < node.priority) {
          node = rotateRight(node);
        }
      } else {
        node.right = insert(node.right, key);
        if (node.right.priority < node.priority) {
          node = rotateLeft(node);
        }
      }
      updateSize(node);
      return node;
    }

    void insert(long key) {
      root = insert(root, key);
    }

    int countLessThan(TreapNode node, long val) {
      if (node == null)
        return 0;
      if (val <= node.key) {
        return countLessThan(node.left, val);
      } else {
        return 1 + countLessThan(node.right, val) + size(node.left);
      }
    }

    int countLessThan(long val) {
      return countLessThan(root, val);
    }
  }

  public static int reversePairs(int[] nums) {
    Treap t = new Treap();
    int cnt = 0;
    for (int i = nums.length-1; i >= 0; i--) {
      cnt += t.countLessThan((long) nums[i]);
      t.insert(2L * nums[i]);
    }
    return cnt;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int nums[] = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = sc.nextInt();
    }
    System.out.println(reversePairs(nums));
    sc.close();
  }
}