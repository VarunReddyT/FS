// f) Write a JAVA Program to find a permutation of the vertices (topological order) which 
// corresponds to the order defined by all edges of the graph
// Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every 
// directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible 
// if the graph is not a DAG.
// For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one 
// topological sorting for a graph. For example, another topological sorting of the following graph is “4 5
// 2 3 1 0”. The first vertex in topological sorting is always a vertex with in degree as 0 (a vertex with no 
// incoming edges).

import java.util.*;

public class TopoSort_1f {
    ArrayList<ArrayList<Integer>> adj;
    int V;

    TopoSort_1f(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }
    void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    void topologicalSort(){
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j : adj.get(i)) {
                inDegree[j]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (topoOrder.size() != V) {
            System.out.println("Graph has a cycle, topological sort not possible.");
        } else {
            System.out.println(topoOrder);  
        }
    }
    public static void main(String[] args) {
        TopoSort_1f g = new TopoSort_1f(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological " + "sort of the given graph");
        g.topologicalSort();

    }
}