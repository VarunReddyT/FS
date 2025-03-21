// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

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
public class MaxProduct{
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
    public static int traverse(BinaryTree root){
        if(root == null){
            return 0;
        }
        return root.data + traverse(root.left) + traverse(root.right);
    }
    public static void check(BinaryTree root, int sum1, int[] arr){
        if(root == null){
            return;
        }
        int lsum = traverse(root.left);
        int rsum = traverse(root.right);
        
        if((lsum)*(rsum+sum1+root.data)>arr[0]){
            arr[0] = (lsum)*(rsum+sum1+root.data);
        }
        if((rsum)*(lsum+sum1+root.data)>arr[0]){
            arr[0] = (rsum)*(lsum+sum1+root.data);
        }
        
        check(root.left,sum1 + root.data,arr);
        check(root.right,sum1 + root.data,arr);
    }
    public static int solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        int lsum = traverse(root.left);
        int rsum = traverse(root.right);
        int[] arr = {1};
        rsum += root.data;
        arr[0] = lsum*rsum;
        check(root.left,rsum,arr);
        rsum -= root.data;
        lsum += root.data;
        arr[0] = Math.max(arr[0],lsum*rsum);
        check(root.right,lsum,arr);
        return arr[0];
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