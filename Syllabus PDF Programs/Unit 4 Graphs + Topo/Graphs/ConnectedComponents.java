import java.util.*;
public class ConnectedComponents {
    static ArrayList<ArrayList<Integer>> adjList;
    int v;
    ConnectedComponents(int v){
        this.v = v;
        adjList = new ArrayList<>();
        for(int i = 0;i<v;i++){
            adjList.add(i,new ArrayList<>());
        }
    }
    public void addEdge(int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    public void getConnectedComponents(){
        boolean[] visited = new boolean[v];
        for(int i = 0;i<v;i++){
            if(!visited[i]){
                dfs(visited,i);
                System.out.println();
            }
        }
    }
    public static void dfs(boolean[] visited, int i){
        visited[i] = true;
        System.out.print(i + " ");
        for(int x : adjList.get(i)){
            if(!visited[x]){
                dfs(visited,x);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        ConnectedComponents c = new ConnectedComponents(v);
        for(int i = 0;i<e;i++){
            c.addEdge(sc.nextInt(),sc.nextInt());
        }
        c.getConnectedComponents();
        sc.close();
    }
}
