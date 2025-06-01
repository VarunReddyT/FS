import java.util.*;

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

public class LCA {
    public Node lowestCommonAscendant(Node root, Node P1, Node P2){
        if(root == null || root.data == P1.data || root.data == P2.data){
            return root;
        }
        Node left = lowestCommonAscendant(root.left, P1, P2);
        Node right = lowestCommonAscendant(root.right, P1, P2);

        return left == null ? right : right == null ? left : root;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        String[] persons = sc.nextLine().split(" ");
        List<Integer> v = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            v.add(Integer.parseInt(arr[i]));
        }
        Node root = new Node(v.get(0));
        Node P1 = new Node(Integer.parseInt(persons[0]));
        Node P2 = new Node(Integer.parseInt(persons[1]));

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int j = 1;
        while(j<n && !q.isEmpty()){
            Node temp = q.poll();
            if(v.get(j) != -1){
                temp.left = new Node(v.get(j));
                q.add(temp.left);
            }
            j++;
            if(j<n && v.get(j) != -1){
                temp.right = new Node(v.get(j));
                q.add(temp.right);
            }
            j++;
        }
        Node res = new LCA().lowestCommonAscendant(root,P1,P2);
        System.out.println(res.data);
        sc.close();
    }
}
