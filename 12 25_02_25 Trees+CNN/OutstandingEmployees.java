// Imagine a company where each employee has a performance score, and 
// the organizational chart is structured as a binary tree with the CEO at the top. 
// An employee is considered "outstanding" if, along the chain of command from the 
// CEO down to that employee, no one has a performance score higher than that 
// employee's score. Your task is to determine the total number of outstanding 
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
//          3
//         / \
//        1   4
//       /   / \
//      3   1   5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4 
//   is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5 
//   is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding because 
//   none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
//        3
//       / 
//      3
//     / \
//    4   2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
//   managers have higher scores.


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
public class OutstandingEmployees{
    static int res = 0;
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
            if (i < levelorder.length && levelorder[i] != -1){
                node.left = new BinaryTree(levelorder[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < levelorder.length && levelorder[i] != -1){
                node.right = new BinaryTree(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static void traverse(BinaryTree root, int max){
        if(root == null){
            return;
        }
        if(root.data >= max){
            max = root.data;
            res++;
        }
        traverse(root.left,max);
        traverse(root.right,max);
    }
    public static int solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        traverse(root,Integer.MIN_VALUE);
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