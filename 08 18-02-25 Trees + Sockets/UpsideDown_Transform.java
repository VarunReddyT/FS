// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4



// import java.util.*;

/*
For reference purpose only.

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

*/
// class Solution {
// 	public TreeNode upsideDownBinaryTree(TreeNode root) {
//         //Write your logic here
//         if(root == null || root.left == null){
//             return root;
//         }
//         TreeNode node = upsideDownBinaryTree(root.left);
//         TreeNode temp = node;
//         while(temp.right != null){
//             temp = temp.right;
//         }
//         temp.left = root.right;
//         temp.right = root;
//         root.left = null;
//         root.right = null;
//         return node;
// 	}
// }
