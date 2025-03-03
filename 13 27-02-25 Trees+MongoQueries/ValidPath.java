// Imagine you are navigating a maze where each path you take has a section with a 
// code. The maze is structured as a series of interconnected rooms, 
// represented as a tree structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this array of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.


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
public class ValidPath{
    public static BinaryTree buildTree(int[] levelorder){
        BinaryTree root = new BinaryTree(levelorder[0]);
        Queue<BinaryTree> queue = new LinkedList<>();
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
    public static boolean preorder(BinaryTree root, int[] path, int curr){
        if(curr == path.length){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.data != path[curr]){
            return false;
        }
        boolean left = preorder(root.left,path,curr+1);
        if(left){
            return true;
        }
        boolean right = preorder(root.right,path,curr+1);
        if(right){
            return true;
        }
        return false;
    }
    public static boolean solve(int[] levelorder, int[] path){
        BinaryTree root = buildTree(levelorder);
        return preorder(root,path,0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        String[] n2 = sc.nextLine().split(" ");
        int[] levelorder = new int[n1.length];
        int[] path = new int[n2.length];
        for(int i = 0;i<n1.length;i++){
            levelorder[i] = Integer.parseInt(n1[i]);
        }
        for(int i = 0;i<n2.length;i++){
            path[i] = Integer.parseInt(n2[i]);
        }
        System.out.print(solve(levelorder,path));
        sc.close();
    }
}