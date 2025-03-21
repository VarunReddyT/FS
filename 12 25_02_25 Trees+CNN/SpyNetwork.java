// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

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
public class SpyNetwork{
    public static BinaryTree solve(String preorder){
        Stack<BinaryTree> stack = new Stack<>();
        boolean flag = false;
        int i = 0;
        int num = 0;
        if(preorder.charAt(i) == '-'){
            flag = true;
            i++;
        }
        while(i<preorder.length() && Character.isDigit(preorder.charAt(i))){
            num = num*10 + Integer.parseInt(preorder.charAt(i)+"");
            i++;
        }
        if(flag == true){
            num -= 2*num;
        }
        BinaryTree root = new BinaryTree(num);
        stack.push(root);
        while(i<preorder.length() && !stack.isEmpty()){
            if(preorder.charAt(i) == '('){
                num = 0;
                i++;
                flag = false;
                if(preorder.charAt(i) == '-'){
                    flag = true;
                    i++;
                }
                while(Character.isDigit(preorder.charAt(i))){
                    num = num*10 + Integer.parseInt(preorder.charAt(i)+"");
                    i++;
                }
                if(flag == true){
                    num -= 2*num;
                }
                stack.push(new BinaryTree(num));
            }
            else if(preorder.charAt(i) == ')'){
                BinaryTree top = stack.pop();
                if(stack.isEmpty()){
                    continue;
                }
                BinaryTree next = stack.peek();
                if(next.left == null && !stack.isEmpty()){
                    next.left = top;
                }
                else if(next.right == null && !stack.isEmpty()){
                    next.right = top;
                }
                i++;    
            }
        }
        return root;
    }
    public static void levelorder(BinaryTree root){
        if(root == null){
            return;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i = 0;i<n;i++){
                BinaryTree top = queue.poll();
                System.out.print(top.data + " ");
                if(top.left != null){
                    queue.offer(top.left);
                }
                if(top.right != null){
                    queue.offer(top.right);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String preorder = sc.nextLine();
        BinaryTree root = solve(preorder);
        levelorder(root);
        sc.close();
    }
}