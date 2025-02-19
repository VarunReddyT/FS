// In an Intelligence Agency, each senior officer supervises either two junior officers 
// or none. The senior officer is assigned a clearance level equal to the higher clearance 
// level of the two junior officers they supervise.

// The clearance levels are represented as integer values in the range [1, 50], and multiple 
// officers may have the same clearance level.

// At the end, all officers (senior and junior) are collectively referred to as agents in the system.

// You are provided with a hierarchical clearance level tree where each node represents 
// an officer's clearance level. The tree structure follows these rules:
// 	- If a node has two children, its clearance level is the maximum of the two children's
// 	  clearance levels.
// 	- If a node has no children, it's clearance level is same as exists.
// 	- The value -1 indicates an empty (null) position.
// Your task is to find the second highest clearance level among all agents in the agency. 
// If no such level exists, return -2.

// Input Format:
// -------------
// A single line of space separated integers, clearance levels of each individual.

// Output Format:
// --------------
// Print an integer, second top agent based on rank.


// Sample Input-1:
// ---------------
// 5 5 4 -1 -1 2 4

// Sample Output-1:
// ----------------
// 4


// Sample Input-2:
// ---------------
// 3 3 3 3 3

// Sample Output-2:
// ----------------
// -2


// import java.util.*;

/*

Node for reference

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

*/

// class Solution
// { 
//     public int getSecond(TreeNode root){
//         if(root == null){
//             return -2;
//         }
//         int left = -2;
//         int right = -2;
//         if(root.left != null){
//             left = root.left.val;
//             if(left == root.val){
//                 left = getSecond(root.left);
//             }
//         }
//         if(root.right != null){
//             right = root.right.val;
//             if(right == root.val){
//                 right = getSecond(root.right);
//             }
//         }
//         return Math.max(left,right);
        
//     }
//     public int secondHighest(TreeNode root) 
//     {
//         // Write your code and return the second top value. 
//         // (return -2 if no second highest.)
//         return getSecond(root);
//     }
// }