// Example-1:
// Input =
// 6 3
// 2 4 3 1 6 5
// Output =
// 4
// Example-2:
// Input =
// 6 2
// 3 2 1 5 6 4
// Output = 
// 5

import java.util.*;

class TreapNode {
    int data;
    int priority;
    TreapNode left;
    TreapNode right;

    TreapNode(int data) {
        this.data = data;
        this.priority = new Random().nextInt(1000);
        this.left = this.right = null;
    }
}

class KthLargest {
    static int k;

    public static TreapNode rightRotate(TreapNode root) {
        TreapNode newRoot = root.left;
        TreapNode temp = newRoot.right;
        newRoot.right = root;
        root.left = temp;
        return newRoot;
    }

    public static TreapNode leftRotate(TreapNode root) {
        TreapNode newRoot = root.right;
        TreapNode temp = newRoot.left;
        newRoot.left = root;
        root.right = temp;
        return newRoot;
    }

    public static TreapNode insertNode(TreapNode root, int data) {
        if (root == null) {
            return new TreapNode(data);
        }
        if (data < root.data) {
            root.left = insertNode(root.left, data);
            if (root.left != null && root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insertNode(root.right, data);
            if (root.right != null && root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    static void inorder(TreapNode root) {
        if (root == null)
            return;
        inorder(root.left);
        k--;
        if (k == 0) {
            System.out.print(root.data);
            return;
        }
        inorder(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        k = n - p + 1;
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        TreapNode root = null;
        for (int a : arr) {
            root = insertNode(root, a);
        }
        inorder(root);
        sc.close();
    }
}
