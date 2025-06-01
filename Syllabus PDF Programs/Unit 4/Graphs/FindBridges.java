import java.util.*; 
import java.util.LinkedList;
public class FindBridges {
    private int v;
    private LinkedList<Integer> adj[];
    private int time = 0;
    private static int NIL = -1;

    @SuppressWarnings("unchecked")
    FindBridges(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i = 0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent){
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for(int v : adj[u]){
            if(!visited[v]){
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);
                low[u] = Math.min(low[u],low[v]);
                if(low[v] > disc[u]){
                    System.out.println(u + " " + v);
                }
            }
            else if(v != parent[u]){
                low[u] =  Math.min(low[u],disc[v]);
            }
        }
    }
    void bridge(){
        boolean[] visited = new boolean[v];
        int[] disc = new int[v];
        int[] low = new int[v];
        int[] parent = new int[v];
        Arrays.fill(parent, NIL);
        Arrays.fill(visited, false);

        for(int i = 0;i<v;i++){
            if(!visited[i]){
                bridgeUtil(i, visited, disc, low, parent);
            }
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        FindBridges g = new FindBridges(v);

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, w);
        }

        System.out.println("Bridges in graph:");
        g.bridge();

        sc.close();
    }
}
