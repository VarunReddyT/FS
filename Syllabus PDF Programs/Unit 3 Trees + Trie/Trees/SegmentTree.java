import java.util.*;

class SegmentTree {
    private int[] tree;
    private int[] nums;
    private int n;

    // Constructor to initialize the Segment Tree
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        // The size of the segment tree is 2 * 2^ceil(log2(n)) - 1
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        this.tree = new int[maxSize];
        buildTree(0, 0, n - 1);
    }

    // Build the Segment Tree
    private void buildTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = nums[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(2 * treeIndex + 1, left, mid); // Build left subtree

        buildTree(2 * treeIndex + 2, mid + 1, right); // Build right subtree
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2]; // Merge results
    }

    // Query the total sum in the range [queryLeft, queryRight]
    public int queryRange(int queryLeft, int queryRight) {
        return queryRange(0, 0, n - 1, queryLeft, queryRight);
    }

    private int queryRange(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        // If the current segment is completely outside the query range
        if (right < queryLeft || left > queryRight) {
            return 0;
        }
        // If the current segment is completely inside the query range
        if (left >= queryLeft && right <= queryRight) {
            return tree[treeIndex];
        }
        // If the current segment overlaps with the query range
        int mid = left + (right - left) / 2;
        int leftSum = queryRange(2 * treeIndex + 1, left, mid, queryLeft, queryRight);
        int rightSum = queryRange(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight);
        return leftSum + rightSum;
    }

    // Update the value at index `index` to `newValue`
    public void update(int index, int newValue) {
        int diff = newValue - nums[index];
        nums[index] = newValue;
        updateTree(0, 0, n - 1, index, diff);
    }

    private void updateTree(int treeIndex, int left, int right, int index, int diff) {
        // If the index is outside the current segment
        if (index < left || index > right) {
            return;
        }

        // If the index is within the current segment
        tree[treeIndex] += diff;
        if (left != right) {
            int mid = left + (right - left) / 2;
            updateTree(2 * treeIndex + 1, left, mid, index, diff); // Update left subtree
            updateTree(2 * treeIndex + 2, mid + 1, right, index, diff); // Update right subtree
        }
    }

    // Get the total sum of the entire array
    public int getTotalSum() {
        return tree[0]; // The root of the segment tree contains the total sum
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        SegmentTree st = new SegmentTree(nums);
        while (q-- > 0) {
            int opt = scan.nextInt();
            if (opt == 1) {
                // call sumrange(s1,s2)
                int s1 = scan.nextInt();
                int s2 = scan.nextInt();
                System.out.println(st.queryRange(s1, s2));
            } else {
                int ind = scan.nextInt();
                int val = scan.nextInt();
                st.update(ind, val);
            }
        }
        scan.close();
    }
}