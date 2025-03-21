// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)

import java.util.*;
import java.util.LinkedList;
class BinaryTree{
    int data;
    BinaryTree left;
    BinaryTree right;
    BinaryTree(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class InPreOrder_Spiral{
    public static ArrayList<Integer> levelOrder(BinaryTree root, int lower, int upper){
        Queue<BinaryTree> queue = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        queue.offer(root);
        int level = 1;
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
            if(level >= lower && level <= upper){
                if(level%2 == 0){
                    Collections.reverse(temp);
                    arr.addAll(temp);
                }
                else{
                    arr.addAll(temp);
                }
            }
            level++;
        }
        return arr;
    }
    public static void solve(int[] inorder, int[] preorder, int n, int lower, int upper){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<n;i++){
            map.put(inorder[i],i);
        }
        BinaryTree root = getTree(map,inorder,preorder,0,n-1,0,n-1);
        System.out.println(levelOrder(root,lower,upper));
    }
    public static BinaryTree getTree(HashMap<Integer,Integer> map, int[] inorder, int[] preorder, int inorderStart, int inorderEnd, int preorderStart, int preorderEnd){
        if(inorderStart > inorderEnd || preorderStart > preorderEnd){
            return null;
        }
        int value = preorder[preorderStart];
        BinaryTree root = new BinaryTree(value);
        int inorderIndex = map.get(value);
        int subtreeSize = inorderIndex - inorderStart;
        root.left = getTree(map,inorder,preorder,inorderStart, inorderStart + subtreeSize -1, preorderStart+1, preorderStart + subtreeSize);
        root.right = getTree(map,inorder,preorder,inorderIndex+1, inorderEnd, preorderStart + subtreeSize+1, preorderEnd);
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] preorder = new int[n];
        for(int i = 0;i<n;i++){
            inorder[i] = sc.nextInt();
        }
        for(int i = 0;i<n;i++){
            preorder[i] = sc.nextInt();
        }
        int lower = sc.nextInt();
        int upper = sc.nextInt();
        solve(inorder,preorder,n,lower,upper);
        sc.close();
    }
}