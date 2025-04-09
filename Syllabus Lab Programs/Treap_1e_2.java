import java.util.*;

public class Treap_1e_2 {
    class TreapNode {
        int key, priority, size;
        TreapNode left, right;

        TreapNode(int key) {
            this.key = key;
            this.priority = (int) (Math.random() * 100);
            this.size = 1;
            this.left = null;
            this.right = null;
        }
    }

    class Treap {
        TreapNode root;

        int size(TreapNode node) {
            if (node == null)
                return 0;
            return node.size;
        }

        void updateSize(TreapNode node) {
            if (node != null) {
                node.size = 1 + size(node.left) + size(node.right);
            }
        }

        TreapNode rotateRight(TreapNode node) {
            TreapNode newNode = node.left;
            TreapNode temp = newNode.right;
            newNode.right = node;
            node.left = temp;
            updateSize(node);
            updateSize(newNode);
            return newNode;
        }

        TreapNode rotateLeft(TreapNode node) {
            TreapNode newNode = node.right;
            TreapNode temp = newNode.left;
            newNode.left = node;
            node.right = temp;
            updateSize(node);
            updateSize(newNode);
            return newNode;
        }

        TreapNode insert(TreapNode node, int key) {
            if (node == null) {
                return new TreapNode(key);
            }
            if (key <= node.key) {
                node.left = insert(node.left, key);
                if (node.left.priority > node.priority) {
                    node = rotateRight(node);
                }
            } else {
                node.right = insert(node.right, key);
                if (node.right.priority > node.priority) {
                    node = rotateLeft(node);
                }
            }
            updateSize(node);
            return node;
        }

        void insert(int key) {
            root = insert(root, key);
        }

        int countLessThanOrEqual(TreapNode node, int key) {
            if (node == null)
                return 0;
            if (node.key <= key) {
                return 1 + countLessThanOrEqual(node.right, key) + size(node.left);
            } else {
                return countLessThanOrEqual(node.left, key);
            }
        }

        int countLessThanOrEqual(int key) {
            return countLessThanOrEqual(root, key);
        }
    }

    public static int countReversePairs(int[] nums) {
        Treap treap = new Treap_1e_2().new Treap();
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            long target = nums[i] > 0 ? (nums[i] - 1) / 2 : nums[i] / 2 - 1;
            count += treap.countLessThanOrEqual((int) target);
            treap.insert(nums[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(countReversePairs(nums));
        sc.close();
    }
}
