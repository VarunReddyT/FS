import java.util.*;

class TreapNode {
    int key, priority;
    TreapNode left, right;
}

public class Treap {
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

    public static TreapNode newNode(int key) {
        TreapNode node = new TreapNode();
        node.key = key;
        node.priority = (int) (Math.random() * 100);
        node.left = null;
        node.right = null;
        return node;
    }

    public static TreapNode insert(TreapNode root, int key) {
        if (root == null) {
            return newNode(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    public static TreapNode delete(TreapNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            if (root.left.priority > root.right.priority) {
                root = rightRotate(root);
                root.right = delete(root.right, key);
            } else {
                root = leftRotate(root);
                root.left = delete(root.left, key);
            }
        }
        return root;
    }

    public static TreapNode search(TreapNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public static void preorder(TreapNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        TreapNode root = null;
        for (int i = 0; i < n; i++) {
            root = insert(root, arr[i]);
        }
        preorder(root);
        System.out.println("Enter item to search:");
        int searchKey = sc.nextInt();
        TreapNode searchResult = search(root, searchKey);
        if (searchResult != null) {
            System.out.println("Found: " + searchResult.key + " with priority: " + searchResult.priority);
        } else {
            System.out.println("Not Found");
        }
        System.out.println("Enter item to delete:");
        int deleteKey = sc.nextInt();
        root = delete(root, deleteKey);
        System.out.println("Preorder after deletion:");
        preorder(root); 
        sc.close();
    }
}
