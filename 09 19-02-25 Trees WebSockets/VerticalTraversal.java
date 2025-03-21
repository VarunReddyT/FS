// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

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
class Pair{
    BinaryTree root;
    int level;
    Pair(BinaryTree root, int level){
        this.root = root;
        this.level = level;
    }
}
public class VerticalTraversal{
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
    public static void getColumns(BinaryTree root, TreeMap<Integer,ArrayList<Integer>> map, int curr){
        if(root == null){
            return;
        }
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root,curr));
        while(!q.isEmpty()){
            Pair node = q.poll();
            BinaryTree currNode = node.root;
            curr = node.level;
            if(map.get(curr) == null){
                map.put(curr, new ArrayList<Integer>());
            }
            map.get(curr).add(currNode.data);
            if(currNode.left != null){
                q.offer(new Pair(currNode.left,curr-1));
            }
            if(currNode.right != null){
                q.offer(new Pair(currNode.right,curr+1));
            }
        }
    }
    public static void solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<>();
        getColumns(root, map,0);
        for(Map.Entry<Integer,ArrayList<Integer>> e : map.entrySet()){
            System.out.print(e.getValue());
        }
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