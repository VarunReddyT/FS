// In a faraway galaxy, interstellar explorers have mapped N planets, numbered from 
// 0 to N-1, interconnected through space routes represented by the given 'routes'. 
// Each element routes[i] = [ai, bi] denotes a direct space route between planet 
// 'ai' and planet 'bi'.

// A Critical Gateway Planet (also known as an articulation point) is a special 
// planet whose removal (along with its space routes) increases the number of 
// disconnected Galactic Regions, thereby isolating groups of planets from each other.

// Given the number of planets (N), number of routes (M) and a list of direct space 
// routes (routes), identify and list all the Critical Gateway Planets within this galaxy.

// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of planets and routes
// Next M lines: Two space separated integers ai and bi.
 
// Output Format:
// --------------
// Print an integer, number of disconnected Galactic Regions.

// Example 1:
// ----------
// Input=
// 5 5
// 0 1
// 1 2
// 2 0
// 1 3
// 3 4

// Output=
// [1, 3]

// Explanation:
// Removing planet 1 disconnects the galaxy into two separate regions: {0,2} and {3,4}.
// Removing planet 3 isolates planet 4, increasing the number of Galactic Regions.


// Example 2:
// -----------
// Input=
// 4 3
// 0 1
// 1 2
// 2 3

// Output=
// [1, 2]

// Explanation:
// Removing planet 1 or 2 increases the Galactic Regions from 1 to 2.


// Constraints:
// - 1 <= n <= 2000
// - 1 <= routes.length <= 5000
// -  routes[i].length == 2
// - 0 <= ai <= bi < n
// - ai != bi
// - No repeated space routes exist (routes).

import java.util.*;

public class ArticulationPoints{
    static int time = 0;
    public static void APUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap, List<List<Integer>> adj){
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;
        for(int i : adj.get(u)){
            if(!visited[i]){
                children++;
                parent[i] = u;
                APUtil(i,visited,disc,low,parent,ap,adj);
                
                low[u] = Math.min(low[u],low[i]);
                
                if(parent[u] == -1 && children>1){
                    ap[u] = true;
                }
                if(parent[u] != -1 && low[i]>=disc[u]){
                    ap[u] = true;
                }
            }
            else if(i != parent[u]){
                low[u] = Math.min(low[u],disc[i]);
            }
        }
    }
    public static void getArticulationPoints(List<List<Integer>> adj, int n, int m){
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        boolean[] ap = new boolean[n];
        Arrays.fill(parent,-1);
        for(int i = 0;i<n;i++){
            if(!visited[i]){
                APUtil(i,visited,disc,low,parent,ap,adj);
            }
        }
        for(int i = 0;i<n;i++){
            if(ap[i]){
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        getArticulationPoints(adj,n,m);
        sc.close();
    }
}