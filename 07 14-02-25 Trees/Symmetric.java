// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

// Sample Output-2:
// ----------------
// false

import java.util.*;
import java.util.LinkedList;
class BinaryTree {
    int data;
    BinaryTree left;
    BinaryTree right;

    BinaryTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Symmetric{
    public static boolean isSymmetric(BinaryTree left, BinaryTree right){
        if(left == null && right == null){
            return true;
        }
        else if((left != null && right == null) || (left == null && right != null)){
            return false;
        }
        if(left.data != right.data){
            return false;
        }
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
        
        
    }
    public static BinaryTree buildTree(int[] levelorder){ 
        if(levelorder.length == 0 || levelorder[0] == -1){
            return null;
        }
        BinaryTree root = new BinaryTree(levelorder[0]);
        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<levelorder.length){
            BinaryTree node = queue.poll();
             if (i < levelorder.length && levelorder[i] != -1) {
                node.left = new BinaryTree(levelorder[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < levelorder.length && levelorder[i] != -1) {
                node.right = new BinaryTree(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static boolean solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        return isSymmetric(root.left,root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        int[] levelorder = new int[n1.length];
        for(int i = 0;i<n1.length;i++){
            levelorder[i] = Integer.parseInt(n1[i]);
        }
        System.out.println(solve(levelorder));
        sc.close();
    }
}