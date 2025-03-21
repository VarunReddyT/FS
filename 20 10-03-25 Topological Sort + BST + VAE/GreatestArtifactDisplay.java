// Imagine you're the chief curator at a renowned museum that houses a rare collection 
// of ancient artifacts. These artifacts are arranged in a special display that 
// follows a strict rule: any artifact positioned to the left of another has a lower 
// value, and any artifact on the right has a higher value. 

// Your task is to transform this display into what is known as a "Greater Artifact 
// Display." In this new arrangement, each artifact’s new value will be its original 
// value plus the sum of the values of all artifacts that are more valuable than it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure 
//        4
//       / \
//      2   6
//     / \ / \
//    1  3 5  7

// Output structure:
//         22
//       /   \
//      27   13
//     / \   / \
//    28 25 18  7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28


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
public class GreatestArtifactDisplay{
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
    public static void getSum(BinaryTree root,int[] currSum){
        if(root == null){
            return;
        }
        getSum(root.right,currSum);
        currSum[0] += root.data;
        root.data = currSum[0];
        getSum(root.left,currSum);
    }
    public static void printSolution(BinaryTree root){
        if(root == null){
            return;
        }
        Queue<BinaryTree> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i = 0;i<n;i++){
                BinaryTree top = q.poll();
                System.out.print(top.data + " ");
                if(top.left != null){
                    q.offer(top.left);
                }
                if(top.right != null){
                    q.offer(top.right);
                }
            }
        }
    }
    public static void solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        int[] currSum = {0};
        getSum(root,currSum);
        printSolution(root);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] levelorder = new int[input.length];
        for(int i = 0;i<input.length;i++){
            levelorder[i] = Integer.parseInt(input[i]);
        }
        solve(levelorder);
        sc.close();
    }
}