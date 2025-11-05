// In an examination hall, the seating is arranged in the form of a tree,
// based on the serial numbers allotted to that room.

// The seating arrangement is filled in the following way:
// 	- left sub node data is always smaller than the current node data.
// 	- right sub node data is always greater than the current node data.
	
// You are given the final tree T.
// Your task is to convert the tree T to Altered Tree such that every data
// of the original tree T is changed to the original data plus sum of all data
// greater than the original data in tree T and return the Altered Tree T.
// and print the altered tree using in-order traversal.

// Your task is to implement the class Solution:
// 	- public BinaryTreeNode alterTree(BinaryTreeNode root): 
// 		return the root node of the altered tree.
// 	- public void inOrder(BinaryTreeNode root):
// 		print the node data of the altered tree.
	
// NOTE: 
// The term data indicates serial number of the aspirant.


// Input Format:
// -------------
// Single line space separated integers, serial numbers of the aspirants.

// Output Format:
// --------------
// Print the inorder traversal of altered bst.


// Sample Input-1:
// ---------------
// 8 3 10 1 6 14 4 7 13

// Sample Output-1:
// ----------------
// 66 65 62 58 52 45 37 27 14


// Sample Input-2:
// ---------------
// 4 2 6 1 3 5 7

// Sample Output-2:
// ----------------
// 28 27 25 22 18 13 7


import java.util.*;

class BinaryTreeNode{
	public int data; 
	public BinaryTreeNode left, right; 
	public BinaryTreeNode(int data){
		this.data = data; 
		left = null; 
		right = null; 
	}
}
public class AlteredTree {
    int sum = 0;
    public BinaryTreeNode alterTree(BinaryTreeNode root) {
        // implement this method
        if(root == null){
            return root;
        }
        alterTree(root.right);
        sum += root.data;
        root.data = sum;
        alterTree(root.left);
        return root;
    }

	public void inOrder(BinaryTreeNode root){
        // implement this method
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
	}
    public BinaryTreeNode insert(BinaryTreeNode root, int data){
        if(root == null){
            return new BinaryTreeNode(data);
        }
        if(data < root.data){
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] values = line.split(" ");
        AlteredTree sol = new AlteredTree();
        BinaryTreeNode root = null;
        for(String val : values){
            int data = Integer.parseInt(val);
            root = sol.insert(root, data);
        }
        root = sol.alterTree(root);
        sol.inOrder(root);
        sc.close();
    }
}
