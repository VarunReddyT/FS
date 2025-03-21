// The Indian Army has established multiple military camps at strategic locations 
// along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
// a hierarchical structure, with a main base camp acting as the root of the network.

// To fortify national security, the Government of India has planned to deploy 
// a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
// boundary of the network.

// Structure of Military Camps:
//     - Each military camp is uniquely identified by an integer ID.
//     - A camp can have at most two direct connections:
//         - Left connection → Represents a subordinate camp on the left.
//         - Right connection → Represents a subordinate camp on the right.
//     - If a military camp does not exist at a specific position, it is 
//       represented by -1
	
// Mission: Deploying the S.H.I.E.L.D.
// Your task is to determine the list of military camp IDs forming the outer 
// boundary of the military network. This boundary must be traversed in 
// anti-clockwise order, starting from the main base camp (root).

// The boundary consists of:
// 1. The main base camp (root).
// 2. The left boundary:
//     - Starts from the root’s left child and follows the leftmost path downwards.
//     - If a camp has a left connection, follow it.
//     - If no left connection exists but a right connection does, follow the right connection.
//     - The leftmost leaf camp is NOT included in this boundary.
// 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
// 4. The right boundary (in reverse order):
//     - Starts from the root’s right child and follows the rightmost path downwards.
//     - If a camp has a right connection, follow it.
//     - If no right connection exists but a left connection does, follow the left connection.
//     - The rightmost leaf camp is NOT included in this boundary.


// Input Format:
// -------------
// space separated integers, military-camp IDs.

// Output Format:
// --------------
// Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


// Sample Input-1:
// ---------------
// 5 2 4 7 9 8 1

// Sample Output-1:
// ----------------
// [5, 2, 7, 9, 8, 1, 4]


// Sample Input-2:
// ---------------
// 11 2 13 4 25 6 -1 -1 -1 7 18 9 10

// Sample Output-2:
// ----------------
// [11, 2, 4, 7, 18, 9, 10, 6, 13]


// Sample Input-3:
// ---------------
// 1 2 3 -1 -1 -1 5 -1 6 7

// Sample Output-3:
// ----------------
// [1, 2, 7, 6, 5, 3]


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

public class BoundaryOfBinaryTree{
    public static void traverse(BinaryTree root, ArrayList<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.data);
        left(root.left,res);
        leaves(root.left,res);
        leaves(root.right,res);
        right(root.right,res);
    }
    public static void leaves(BinaryTree root, ArrayList<Integer> res){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            res.add(root.data);
        }
        leaves(root.left,res);
        leaves(root.right,res);
        
    }
    public static void left(BinaryTree root, ArrayList<Integer> res){
        if(root==null || (root.left == null && root.right == null)){
            return;
        }
        res.add(root.data);
        if(root.left == null){
            left(root.right,res);
        }
        else{
            left(root.left,res);
        }
    }
    public static void right(BinaryTree root, ArrayList<Integer> res){
        if(root==null || (root.left == null && root.right == null)){
            return;
        }
        if(root.right == null){
            right(root.left,res);
        }
        else{
            right(root.right,res);
        }
        res.add(root.data);
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
        traverse(root,res);
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