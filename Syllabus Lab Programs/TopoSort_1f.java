// Write a JAVA Program to find a permutation of the vertices (topological order) which 
// corresponds to the order defined by all edges of the graph
// Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every 
// directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible 
// if the graph is not a DAG.
// For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one 
// topological sorting for a graph. For example, another topological sorting of the following graph is “4 5
// 2 3 1 0”. The first vertex in topological sorting is always a vertex with in degree as 0 (a vertex with no 
// incoming edges)


import java.util.*;

public class TopoSort_1f {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;
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
    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        Integer i;
        Iterator<Integer> it = adj.get(v).iterator();
        while(it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }

        }
        stack.push(v);
    }
    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
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

        System.out.println("Following is a Topological sort of the given graph:");
        g.topologicalSort();
    }
}
