// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

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

public class PrePost{
     public static ArrayList<ArrayList<Integer>> levelOrder(BinaryTree root){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<>();
            int n = queue.size();
            for(int i = 0;i<n;i++){
                BinaryTree node = queue.poll();
                temp.add(node.data);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
    public static BinaryTree helper(int[] preorder, int[] postorder, int preorderStart, int preorderEnd, int postorderStart,HashMap<Integer, Integer> map){
        if(preorderStart > preorderEnd){
            return null;
        }
        int val = preorder[preorderStart];
        BinaryTree root = new BinaryTree(val);
        if(preorderStart == preorderEnd){
            return root;
        }
        int idx = map.get(preorder[preorderStart + 1]);
        int leftTreeSize = idx - postorderStart + 1;
        root.left = helper(preorder,postorder,preorderStart+1,preorderStart + leftTreeSize, postorderStart,map);
        root.right = helper(preorder,postorder,preorderStart+leftTreeSize+1,preorderEnd, idx+1 ,map);
        return root;
        
    }
    public static ArrayList<ArrayList<Integer>> solve(int[] preorder, int[] postorder){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<postorder.length;i++){
            map.put(postorder[i],i);
        }
        BinaryTree root = helper(preorder,postorder,0,preorder.length-1,0,map);
        return levelOrder(root);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        String[] n2 = sc.nextLine().split(" ");
        int[] preorder = new int[n1.length];
        int[] postorder = new int[n2.length];
        for(int i = 0;i<n1.length;i++){
            preorder[i] = Integer.parseInt(n1[i]);
            postorder[i] = Integer.parseInt(n2[i]);
        }
        System.out.println(solve(preorder,postorder));
        sc.close();
    }
}