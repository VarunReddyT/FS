// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

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
public class InPostOrder{
    public static HashMap<Integer,ArrayList<Integer>> levelOrder(BinaryTree root){
        HashMap<Integer,ArrayList<Integer>> levelMap = new HashMap<>();
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i = 0;i<n;i++){
                BinaryTree node = queue.poll();
                if(!levelMap.containsKey(level)){
                    levelMap.put(level, new ArrayList<Integer>());
                }
                levelMap.get(level).add(node.data);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return levelMap;
    }
    public static void solve(int[] inorder, int[] postorder, int n, int q, int[][] queries){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<n;i++){
            map.put(inorder[i],i);
        }
        BinaryTree root = getTree(map,inorder,postorder,0,n-1,0,n-1);
        HashMap<Integer,ArrayList<Integer>> levelMap = levelOrder(root);
        for(int[] query : queries){
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = query[0];i<=query[1];i++){
                arr.addAll(levelMap.get(i));
            }
            System.out.println(arr);
        }
        
    }
    public static BinaryTree getTree(HashMap<Integer,Integer> map, int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postorderStart, int postorderEnd){
        if(inorderStart > inorderEnd || postorderStart > postorderEnd){
            return null;
        }
        int value = postorder[postorderEnd];
        BinaryTree root = new BinaryTree(value);
        int inorderIndex = map.get(value);
        int subtreeSize = inorderIndex - inorderStart;
        root.left = getTree(map,inorder,postorder,inorderStart, inorderStart + subtreeSize -1, postorderStart, postorderStart + subtreeSize - 1);
        root.right = getTree(map,inorder,postorder,inorderIndex+1, inorderEnd, postorderStart + subtreeSize, postorderEnd-1);
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] postorder = new int[n];
        for(int i = 0;i<n;i++){
            inorder[i] = sc.nextInt();
        }
        for(int i = 0;i<n;i++){
            postorder[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for(int i = 0;i<q;i++){
            queries[i] = new int[]{sc.nextInt(),sc.nextInt()};
        }
        solve(inorder,postorder,n,q,queries);
        sc.close();
    }
}