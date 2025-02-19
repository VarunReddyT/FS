import java.util.*;

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
public class UpsideDown{
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
                queue.offer(node.left);
            }
            i++;
        }
        return root;
    }
    public static BinaryTree flip(BinaryTree root){
        if(root == null || root.left == null){
            return root;
        }
        BinaryTree node = flip(root.left);
        BinaryTree temp = node;
        while(temp.right != null){
            temp = temp.right;
        }
        temp.left = root.right;
        temp.right = root;
        root.left = null;
        root.right = null;
        return node;
    }
    public static void levelOrderTraversal(BinaryTree root) {
        if (root == null) return;
        
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            System.out.print(node.data + " ");
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    public static void solve(int[] levelorder){
        BinaryTree root = buildTree(levelorder);
        BinaryTree newRoot = flip(root);
        levelOrderTraversal(newRoot);
        
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