import java.util.*;

public class FindArticulationPoints {
    static int time;

    void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void APUtil(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited, int[] disc, int[] low, int parent, boolean isAP[]) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                APUtil(adj, v, visited, disc, low, parent, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (parent != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            } else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }

    static void AP(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        int par = -1;
        for (int u = 0; u < V; u++)
            if (visited[u] == false)
                APUtil(adj, u, visited, disc, low, par, isAP);
        for (int u = 0; u < V; u++)
            if (isAP[u] == true)
                System.out.print(u + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of vertices ");
        int V = sc.nextInt();
        System.out.println("enter number of edges");
        int e = sc.nextInt();
        FindArticulationPoints g = new FindArticulationPoints();
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<ArrayList<Integer>>(V);
        for (int i = 0; i < V; i++) {
            adj1.add(new ArrayList<Integer>());
        }
        System.out.println("enter edges");
        for (int i = 0; i < e; i++) {
            int end1 = sc.nextInt();
            int end2 = sc.nextInt();
            g.addEdge(adj1, end1, end2);
        }
        System.out.println("Articulation points in first graph");
        AP(adj1, V);
        sc.close();
    }

}
