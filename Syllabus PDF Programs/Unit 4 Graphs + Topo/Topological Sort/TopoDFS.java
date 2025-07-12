import java.util.*;

public class TopoDFS {
    static void addEdge(List<Integer>[] adj, int u, int v){
        adj[u].add(v);
    }
    static void dfs(List<Integer>[] adj, List<Integer> vis, int node, int n, Stack<Integer> stack){
        vis.set(node,1);
        for(int i : adj[node]){
            if(vis.get(i)==0){
                dfs(adj,vis,i,n,stack);
            }
        }
        stack.push(node);
    }
    Stack<Integer> topo_sort(List<Integer>[] adj, int n){
        List<Integer> vis = new ArrayList<>();
        for(int i = 0;i<n;i++){
            vis.add(0);
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<n;i++){
            if(vis.get(i)==0){
                dfs(adj,vis,i,n,stack);
            }
        }
        return stack;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of vertices ");
        int n = sc.nextInt();
        System.out.println("enter number of edges");
        int e = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        System.out.println("enter edges");
        for (int i = 0; i < e; i++) {
            int end1 = sc.nextInt();
            int end2 = sc.nextInt();
            addEdge(adj, end1, end2);
        }
        TopoDFS ts = new TopoDFS();
        Stack<Integer> ans = ts.topo_sort(adj, n);
        while (!ans.isEmpty()) {
            int node = ans.pop();
            System.out.print(node + " ");
        }
        sc.close();
    }
}
