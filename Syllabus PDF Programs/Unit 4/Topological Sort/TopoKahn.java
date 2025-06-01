import java.util.*;
import java.util.LinkedList;

public class TopoKahn {
    List<Integer> topologicalSort(int n, ArrayList<ArrayList<Integer>> adj){
        List<Integer> topoOrder = new ArrayList<>();
        int[] indegree = new int[n];
        for(int i = 0;i<n;i++){
            for(int node : adj.get(i)){
                indegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<n;i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        int count = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            topoOrder.add(node);
            count++;
            for(int neighbour : adj.get(node)){
                indegree[neighbour]--;
                if(indegree[neighbour]==0){
                    q.add(neighbour);
                }
            }
        }
        if(count != n){
            return new ArrayList<>();
        }
        return topoOrder;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int N = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        System.out.println("Enter edges (from to):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }
        TopoKahn sol = new TopoKahn();
        List<Integer> result = sol.topologicalSort(N, adj);
        if (result.isEmpty()) {
            System.out.println("Graph contains a cycle. Topological sort not possible.");
        } else {
            System.out.println("Topological order is:");
            for (int node : result) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
