// e) Write a JAVA Program to implement TREAP with its operations
// Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a 
// pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
// Example 1:
// Input: nums = [1,3,2,3,1] 
// Output: 2
// Example 2:
// Input: nums = [2,4,3,5,1] 
// Output: 3
// Constraints:
// 1 <= nums.length <= 5 * 104
// -2^31 <= nums[i] <= 2^31 – 1

import java.util.*;

public class Treap_1e {
    class TreapNode {
        int key, priority, count;
        TreapNode left, right;

        public TreapNode(int key) {
            this.key = key;
            this.priority = (int) (Math.random() * 100);
            this.count = 1;
            this.left = null;
            this.right = null;
        }

        public int getCount(TreapNode node) {
            if (node == null)
                return 0;
            return node.count;
        }

        public void update(TreapNode node) {
            if (node != null) {
                node.count = 1 + getCount(node.left) + getCount(node.right);
            }
        }

        public TreapNode[] split(TreapNode node, int key) {
            TreapNode[] res = new TreapNode[2];
            if (node == null) {
                return res;
            }
            if (node.key < key) {
                TreapNode[] rightSplit = split(node.right, key);
                node.right = rightSplit[0];
                res[0] = node;
                res[1] = rightSplit[1];
            } else {
                TreapNode[] leftSplit = split(node.left, key);
                node.left = leftSplit[1];
                res[0] = leftSplit[0];
                res[1] = node;
            }
            update(node);
            return res;
        }

        public TreapNode merge(TreapNode left, TreapNode right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            if (left.priority > right.priority) {
                left.right = merge(left.right, right);
                update(left);
                return left;
            } else {
                right.left = merge(left, right.left);
                update(right);
                return right;
            }
        }

        public TreapNode insert(TreapNode root, TreapNode newNode) {
            if (root == null) {
                return newNode;
            }
            if (newNode.priority > root.priority) {
                TreapNode[] splitRes = split(root, newNode.key);
                newNode.left = splitRes[0];
                newNode.right = splitRes[1];
                update(newNode);
                return newNode;
            }
            if (newNode.key < root.key) {
                root.left = insert(root.left, newNode);
            } else {
                root.right = insert(root.right, newNode);
            }
            update(root);
            return root;
        }

        public int countLessThanOrEqual(TreapNode root, int key) {
            TreapNode[] splitRes = split(root, key + 1);
            int count = getCount(splitRes[0]);
            root = merge(splitRes[0], splitRes[1]);
            return count;
        }

        public int countReversePairs(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            TreapNode root = null;
            int res = 0;
            for(int i = nums.length-1;i>=0;i--){
                int target = nums[i]/2;
                res += countLessThanOrEqual(root, target);
                TreapNode newNode = new TreapNode(nums[i]);
                root = insert(root, newNode);
            }
            return res;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Treap_1e.TreapNode treapNode = new Treap_1e().new TreapNode(0);
        System.out.println(treapNode.countReversePairs(arr));
        sc.close();
    }
}
