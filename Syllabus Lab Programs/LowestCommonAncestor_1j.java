// j) Write a JAVA Program to find the lowest common ancestor of a binary tree Given a binary 
// tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
// According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
// two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node 
// to be a descendant of itself).”
// Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4]
// Example 1:
// Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 Output: 3
// Explanation: The LCA of nodes 5 and 1 is 3.
// Example 2:
// Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 Output: 5
// Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to 
// the LCA definition.
// Note:
// All of the nodes' values will be unique.
// p and q are different and both values will exist in the binary tree.

import java.util.*;
import java.util.LinkedList;

class Node {
    int data;
    Node left, right;
    
    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}
public class LowestCommonAncestor_1j {
    Node lowestCommonAscendant(Node root, Node P1, Node P2) {
        if(root == null){
            return null;
        }
        if(P1.data == root.data || P2.data == root.data){
            return root;
        }
        Node left = lowestCommonAscendant(root.left, P1, P2);
        Node right = lowestCommonAscendant(root.right, P1, P2);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr= sc.nextLine().split(" "); 
        String[] persons = sc.nextLine().split(" ");
        List<Integer> v = new ArrayList<>(); 
        int n=arr.length;
        for (int i = 0; i < n; i++){
            v.add(Integer.parseInt(arr[i]));
        }
        Node root = new Node(v.get(0));
        Node P1 = new Node(Integer.parseInt(persons[0]));
        Node P2 = new Node(Integer.parseInt(persons[1]));
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int j = 1;
        while(j<n && !q.isEmpty()){
            Node temp = q.poll();
            if(v.get(j) != -1){
                temp.left = new Node(v.get(j));
                q.add(temp.left);
            }
            j++;
            if(j<n && v.get(j) != -1){
                temp.right = new Node(v.get(j));
                q.add(temp.right);
            }
            j++;
        }

        Node res = new LowestCommonAncestor_1j().lowestCommonAscendant(root, P1, P2);
        System.out.println(res.data);
        sc.close();
    }
}
