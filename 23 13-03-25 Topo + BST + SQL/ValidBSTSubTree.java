// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5
 
// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.


import java.util.*;
import java.util.LinkedList;
class BST{
    int data;
    BST left;
    BST right;
    BST(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class ValidBSTSubTree{
    public static BST buildTree(int[] levelorder){
        BST root = new BST(levelorder[0]);
        Queue<BST> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<levelorder.length){
            BST node = queue.poll();
            if(i<levelorder.length && levelorder[i]!=-1){
                node.left = new BST(levelorder[i]);
                queue.offer(node.left);
            }
            i++;
            if(i<levelorder.length && levelorder[i]!=-1){
                node.right = new BST(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static int traverse(BST root, int[] sum){
        if(root == null){
            return 0;
        }
        int l = traverse(root.left,sum);
        int r = traverse(root.right,sum);
        if(l != -1 && r != -1){
            if(l!=0 && root.data <= root.left.data){
                return -1;
            }
            if(r!=0 && root.data >= root.right.data){
                return -1;
            }
        }
        else{
            return -1;
        }
        int max = l+r+root.data;
        sum[0] = Math.max(sum[0],max);
        return max;
    }
    public static int getSum(int[] arr){
        BST root = buildTree(arr);
        int[] sum = {0};
        traverse(root,sum);   
        return sum[0];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] arr = new int[input.length];
        for(int i = 0;i<input.length;i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getSum(arr));
        sc.close();
    }
}