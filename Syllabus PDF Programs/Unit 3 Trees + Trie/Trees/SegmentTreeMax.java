class SegmentTreeMax {
    private int[] tree;
    private int[] nums;
    private int n;

    // Constructor to initialize the Segment Tree
    public SegmentTreeMax(int[] nums) {
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

            tree[treeIndex] = nums[left]; // Leaf node stores the value
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(2 * treeIndex + 1, left, mid); // Build left subtree
        buildTree(2 * treeIndex + 2, mid + 1, right); // Build right subtree
        // Merge results: store the maximum of left and right subtrees
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    // Query the maximum value in the range [queryLeft, queryRight]
    public int queryRangeMax(int queryLeft, int queryRight) {
        return queryRangeMax(0, 0, n - 1, queryLeft, queryRight);
    }

    private int queryRangeMax(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        // If the current segment is completely outside the query range
        if (right < queryLeft || left > queryRight) {
            return Integer.MIN_VALUE; // Return minimum value to avoid affecting the result
        }
        // If the current segment is completely inside the query range
        if (left >= queryLeft && right <= queryRight) {
            return tree[treeIndex];
        }
        // If the current segment overlaps with the query range
        int mid = left + (right - left) / 2;
        int leftMax = queryRangeMax(2 * treeIndex + 1, left, mid, queryLeft, queryRight);
        int rightMax = queryRangeMax(2 * treeIndex + 2, mid + 1, right, queryLeft,
                queryRight);
        return Math.max(leftMax, rightMax);
    }

    // Update the value at index `index` to `newValue`
    public void update(int index, int newValue) {
        nums[index] = newValue;
        updateTree(0, 0, n - 1, index, newValue);
    }

    private void updateTree(int treeIndex, int left, int right, int index, int newValue) {
        // If the index is outside the current segment
        if (index < left || index > right) {
            return;
        }

        // If the current segment is a leaf node
        if (left == right) {
            tree[treeIndex] = newValue;
            return;
        }
        // If the index is within the current segment
        int mid = left + (right - left) / 2;
        updateTree(2 * treeIndex + 1, left, mid, index, newValue); // Update left subtree
        updateTree(2 * treeIndex + 2, mid + 1, right, index, newValue); // Update right subtree
        // Merge results: update the current node with the maximum of left and right
        // subtrees
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    // Get the maximum value of the entire array
    public int getMax() {
        return tree[0]; // The root of the segment tree contains the maximum value
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 7, 9, 11 };
        SegmentTreeMax st = new SegmentTreeMax(nums);
        // Query the maximum value in the range [1, 4]
        System.out.println("Maximum value in range [1, 4]: " + st.queryRangeMax(1, 4)); // Output: 9
        // Update the element at index 2 to 10
        st.update(2, 10);
        // Query the maximum value in the range [1, 4] after update
        System.out.println("Maximum value in range [1, 4] after update: " +
                st.queryRangeMax(1, 4)); // Output: 10
        // Get the maximum value of the entire array
        System.out.println("Maximum value of the array: " + st.getMax()); // Output: 11
    }
}