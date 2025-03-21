// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

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

public class RightSideView{
     public static void traverse(BinaryTree root, int level, ArrayList<Integer> res){
        if(root == null){
            return;
        }
        if(level == res.size()){
            res.add(root.data);
        }
        traverse(root.right,level+1,res);
        traverse(root.left,level+1,res);
        
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
    public static ArrayList<Integer> solve(int[] levelorder){
        if(levelorder[0] == -1){
            return new ArrayList<>();
        }
        BinaryTree root = buildTree(levelorder);
        ArrayList<Integer> res = new ArrayList<>();
        traverse(root,0,res);
        return res;
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