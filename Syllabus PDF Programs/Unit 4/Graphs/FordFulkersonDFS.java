import java.util.*;

public class FordFulkersonDFS {
    static int V;
    static boolean dfs(int[][] residualGraph, int s, int t, int[] parent){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        parent[s] = -1;

        while(!stack.isEmpty()){
            int u = stack.pop();
            for(int v = 0;v<V;v++){
                if(!visited[v] && residualGraph[u][v] > 0){
                    stack.push(v);
                    parent[v] = u;
                    visited[v] = true;
                    if(v==t){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    int fordFulkerson(int[][] graph, int s, int t){
        int u, v;
        int[][] residualGraph = new int[V][V];
        for(u = 0;u<V;u++){
            for(v = 0;v<v;v++){
                residualGraph[u][v] = graph[u][v];
            }
        }
        int[] parent = new int[V];
        int maxFlow = 0;

        while(dfs(residualGraph,s,t,parent)){
            int pathFlow = Integer.MAX_VALUE;
            for(v = t;v!=s;v=parent[v]){
                u = parent[v];
                pathFlow = Math.min(pathFlow,residualGraph[u][v]);
            }

            for(v = t;v!=s;v=parent[v]){
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices ");
        V = sc.nextInt();
        int graph[][] = new int[V][V];
        System.out.println("Enter the adjacency matrix of the directed graph");
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = sc.nextInt();
        System.out.println("Enter source and sink ");
        int s = sc.nextInt();
        int t = sc.nextInt();
        System.out.println(new FordFulkersonDFS().fordFulkerson(graph, s, t));
        sc.close();
    }
}
