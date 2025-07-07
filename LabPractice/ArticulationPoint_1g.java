// g) Write a JAVA Program to find all the articulation points of a graph
// We are given an undirected graph. An articulation point (or cut vertex) is defined as a vertex which, when 
// removed along with associated edges, makes the graph disconnected (or more precisely, increases the 
// number of connected components in the graph). The task is to find all articulation points in the given 
// graph.

import java.util.*;

public class ArticulationPoint_1g {
    ArrayList<ArrayList<Integer>> adj;
    int V;
    int time = 0;
    public ArticulationPoint_1g(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    public void APUtil(int u, boolean[] visited, int[] disc, int[] low, int parent, boolean[] ap) {
        visited[u] = true;
        low[u] = disc[u] = ++time;
        int children = 0;
        for(int v : adj.get(u)){
            if(!visited[v]){
                children++;
                APUtil(v, visited, disc, low, u, ap);
                low[u] = Math.min(low[u], low[v]);
                if(parent != -1 && low[v] >= disc[u]){
                    ap[u] = true;
                }
            }
            else if(v != parent){
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if(parent == -1 && children > 1){
            ap[u] = true;
        }
    }
    public void findArticulationPoints() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        int parent = -1;
        boolean[] ap = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                APUtil(i, visited, disc, low, parent, ap);
            }
        }
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        ArticulationPoint_1g g = new ArticulationPoint_1g(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Articulation points in the graph: ");
        g.findArticulationPoints();
    }
}