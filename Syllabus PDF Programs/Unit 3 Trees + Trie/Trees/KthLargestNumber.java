import java.util.*;

class TreapNode {
    int data, priority;
    TreapNode left, right;

    TreapNode(int data) {
        this.data = data;
        this.priority = new Random().nextInt(1000);
        this.left = this.right = null;
    }
}

public class KthLargestNumber {
    static int k;

    public static TreapNode rotateRight(TreapNode root) {
        TreapNode newRoot = root.left;
        TreapNode temp = newRoot.right;
        newRoot.right = root;
        root.left = temp;
        return newRoot;
    }

    public static TreapNode rotateLeft(TreapNode root) {
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
            if (root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else {
            root.right = insertNode(root.right, data);
            if (root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }
        return root;
    }

    public static void inorder(TreapNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        k--;
        if (k == 0) {
            System.out.println(root.data);
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
        for (int i = 0; i < n; i++) {
            root = insertNode(root, arr[i]);
        }
        inorder(root);
        sc.close();
    }
}
