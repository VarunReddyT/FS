import java.util.*;

public class SegmentTreeLazyPropagation {
    private int[] tree;
    private int[] lazy;
    private int[] nums;
    private int n;

    public SegmentTreeLazyPropagation(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[4 * n]; // Allocate enough space for the segment tree
        this.lazy = new int[4 * n]; // Allocate space for lazy propagation
        buildTree(0, 0, n - 1);
    }

    private void buildTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = nums[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(2 * treeIndex + 1, left, mid);
        buildTree(2 * treeIndex + 2, mid + 1, right);
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    // Push pending updates before using the node
    private void push(int treeIndex, int left, int right) {
        if (lazy[treeIndex] != 0) {
            tree[treeIndex] += (right - left + 1) * lazy[treeIndex];
            if (left != right) {
                lazy[2 * treeIndex + 1] += lazy[treeIndex];
                lazy[2 * treeIndex + 2] += lazy[treeIndex];
            }
            lazy[treeIndex] = 0;
        }
    }

    public int queryRange(int queryLeft, int queryRight) {
        return queryRange(0, 0, n - 1, queryLeft, queryRight);
    }

    private int queryRange(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        push(treeIndex, left, right);

        if (right < queryLeft || left > queryRight)
            return 0;
        if (queryLeft <= left && right <= queryRight)
            return tree[treeIndex];

        int mid = left + (right - left) / 2;
        int leftSum = queryRange(2 * treeIndex + 1, left, mid, queryLeft, queryRight);
        int rightSum = queryRange(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight);
        return leftSum + rightSum;
    }

    // Range update: add value to range [l, r]
    public void updateRange(int updateLeft, int updateRight, int val) {
        updateRange(0, 0, n - 1, updateLeft, updateRight, val);
    }

    private void updateRange(int treeIndex, int left, int right, int updateLeft, int updateRight, int val) {
        push(treeIndex, left, right);

        if (right < updateLeft || left > updateRight)
            return;

        if (updateLeft <= left && right <= updateRight) {
            lazy[treeIndex] += val;
            push(treeIndex, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        updateRange(2 * treeIndex + 1, left, mid, updateLeft, updateRight, val);
        updateRange(2 * treeIndex + 2, mid + 1, right, updateLeft, updateRight, val);
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    public int getTotalSum() {
        return tree[0];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = scan.nextInt();

        SegmentTreeLazyPropagation st = new SegmentTreeLazyPropagation(nums);

        while (q-- > 0) {
            int opt = scan.nextInt();
            if (opt == 1) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                System.out.println(st.queryRange(l, r));
            } else if (opt == 2) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                int val = scan.nextInt();
                st.updateRange(l, r, val);
            }
        }
        scan.close();
    }
}
