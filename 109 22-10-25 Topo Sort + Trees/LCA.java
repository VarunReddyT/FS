//           CEO 
//     Manager1 Manager2
//    Emp1 Emp2 Emp3 Emp4

import java.util.*;

class Node{
    String value;
    Node left;
    Node right;
    Node(String value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class LCA {
    public static Node findLCA(Node root, String a, String b){
        if(root == null || root.value == a || root.value == b){
            return root;
        }
        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a, b);
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        return right;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node root = new Node("CEO");
        root.left = new Node("Manager1");
        root.right = new Node("Manager2");
        root.left.left = new Node("Emp1");
        root.left.right = new Node("Emp2");
        root.right.left = new Node("Emp3");
        root.right.right = new Node("Emp4");

        String input = sc.nextLine();
        String inp = input.substring(4, input.length() - 1);
        String[] arr = inp.split(", ");
        Node lca = findLCA(root, arr[0], arr[1]);
        System.out.println(lca.value);
        sc.close();
    }
}
