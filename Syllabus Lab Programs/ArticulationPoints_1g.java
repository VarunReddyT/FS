// Write a JAVA Program to find all the articulation points of a graph
// We are given an undirected graph. An articulation point (or cut vertex) is defined as a vertex which, when 
// removed along with associated edges, makes the graph disconnected (or more precisely, increases the 
// number of connected components in the graph). The task is to find all articulation points in the given 
// graph.

import java.util.*;
public class ArticulationPoints_1g {
    static int time;
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    static void APUtil(int u, boolean[] visited, int[] disc, int[] low, boolean[] isAP, int par, ArrayList<ArrayList<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for(Integer v : adj.get(u)){
            if(!visited[v]){
                children++;
                APUtil(v, visited, disc, low, isAP, u, adj);
                low[u] = Math.min(low[u], low[v]);
                if(par != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            }
            else if(v != par) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if(par == -1 && children > 1) {
            isAP[u] = true;
        }
    }
    static void AP(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        int par = -1;
        time = 0;

        for(int u = 0; u < V; u++) {
            if(!visited[u]) {
                APUtil(u, visited, disc, low, isAP, par, adj);
            }
        }
        for(int i = 0; i < V; i++) {
            if(isAP[i]) {
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        addEdge(adj, 1, 0);
        addEdge(adj, 0, 2);
        addEdge(adj, 2, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 3, 4);

        System.out.println("Articulation points in the graph:");
        AP(adj, V);
    }
}
