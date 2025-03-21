// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5

// Sample Output-2:
// ----------------
// [3, 2, 1]

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

public class LeftSideView{
     public static void traverse(BinaryTree root, int level, ArrayList<Integer> res){
        if(root == null){
            return;
        }
        if(level == res.size()){
            res.add(root.data);
        }
        traverse(root.left,level+1,res);
        traverse(root.right,level+1,res);
        
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