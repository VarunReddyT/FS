// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7

import java.util.*;
import java.util.LinkedList;
class BinaryTree{
    int data;
    BinaryTree left;
    BinaryTree right;
    BinaryTree(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class levelOrderInsertionNull{
    public static void insertNode(BinaryTree root, int val){
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BinaryTree node = queue.poll();
            if(node.data==-1)continue;
            if(node.left == null ){
                node.left = new BinaryTree(val);
                break;
            }
            queue.add(node.left);
            if(node.right == null){
                node.right = new BinaryTree(val);
                break;
            }
            queue.add(node.right);
        }
    }
    public static void printInOrder(BinaryTree root){
        if(root == null || root.data == -1){
            return;
        }
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        if(arr.length == 0){
            sc.close();
            return ;
        }
        BinaryTree root = new BinaryTree(Integer.parseInt(arr[0]));
        for(int i = 1;i<arr.length;i++){
            insertNode(root,Integer.parseInt(arr[i]));
        }
        
        printInOrder(root);
        sc.close();
    }
}