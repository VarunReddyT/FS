// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7

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

public class levelOrderInsertion{
    public static void printInOrder(BinaryTree root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
    public static void insertNode(BinaryTree root, int val){
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BinaryTree node = queue.poll();
            if(node.left == null){
                node.left = new BinaryTree(val);
                break;
            }
            else{
                queue.add(node.left);
            }
            if(node.right == null){
                node.right = new BinaryTree(val);
                break;
            }
            else{
                queue.add(node.right);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        BinaryTree root = new BinaryTree(arr[0]);
        for(int i = 1;i<n;i++){
            if(arr[i] != -1){
                insertNode(root,arr[i]);
            }
        }
        printInOrder(root);
        sc.close();
    }
}