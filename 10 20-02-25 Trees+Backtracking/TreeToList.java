// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6


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
public class TreeToList{
    public static BinaryTree buildTree(int[] levelorder){
        BinaryTree root = new BinaryTree(levelorder[0]);
        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<levelorder.length){
            BinaryTree node = queue.poll();
            if(i<levelorder.length && levelorder[i]!=-1){
                node.left = new BinaryTree(levelorder[i]);
                queue.offer(node.left);
            }
            i++;
            if(i<levelorder.length && levelorder[i]!=-1){
                node.right = new BinaryTree(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static BinaryTree flattenTree(BinaryTree root){
        if(root == null || root.left == null){
            return root;
        }
        BinaryTree left = flattenTree(root.left);
        BinaryTree right = flattenTree(root.right);
        if(left == null){
            return root;
        }
        if(right == null){
            root.right = root.left;
            root.left = null;
            return root;
        }
        BinaryTree temp = root;
        temp.right = left;
        while(temp.right!=null){
            temp = temp.right;
        }
        temp.right = right;
        root.left = null;
        return root;
    }
    //  if(root==null) return;
    //     Stack<TreeNode> st = new Stack<>();
    //     st.push(root);
    //     while(!st.isEmpty()){
    //         TreeNode node = st.pop();
    //         if(node.right!=null) st.push(node.right);
    //         if(node.left!=null) st.push(node.left);
    //         node.right = st.isEmpty()? null : st.peek();
    //         node.left = null;
        // }
    public static void display(BinaryTree root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        display(root.right);
    }
    public static void solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        BinaryTree newRoot = flattenTree(root);
        display(newRoot);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        int[] levelorder = new int[n1.length];
        for(int i = 0;i<n1.length;i++){
            levelorder[i] = Integer.parseInt(n1[i]);
        }
        solve(levelorder);
        sc.close();
    }
}