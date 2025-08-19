// There are N cities in a state.
// The cities are connected with two types of roadways:
// 1) concrete roadway 2) asphalt roadway.
// The roadways may be parallel.

// You are given the lists of concrete roadways and asphalt roadways
// between the cities. All roadways are unidirectional.
// Concrete_Roadway[i,j] indiactes: a concrete road from city-i to city-j. Similarly,
// Asphalt[i,j] indiactes: an asphalt road from city-i to city-j. Similarly,

// Your task is to find and return the list of lengths of the shortest paths from 
// city-0 to city-P, where P start from 0 to N-1. And the path should contains 
// alternative roadways like as follows: concrete - asphalt - concrete -asphalt --etc
// or vice versa. If there is no such shortest path exist print -1.

// Input Format:
// -------------
// Line-1: 3 space separated integers N, C & A, Number of cities, routes between the cities.
// 		number of concrete roads and number of asphalt roads
// Next C lines: Two space separated integers, concrete road between city-i to city-j.		
// Next A lines: Two space separated integers, asphalt road between city-i to city-j.		

// Output Format:
// --------------
// Print the list of lengths, the shortest paths.


// Sample Input-1:
// ---------------
// 4 2 1
// 0 1
// 2 3
// 1 2

// Sample Output-1:
// ----------------
// 0 1 2 3

// Sample Input-2:
// ---------------
// 4 2 1
// 1 0
// 2 3
// 1 2

// Sample Output-2:
// ----------------
// 0 -1 -1 -1 


// Sample Input-3:
// ---------------
// 4 3 2
// 1 0
// 1 2
// 2 3
// 0 1
// 1 2

// Sample Output-3:
// ----------------
// 0 1 2 -1


import java.util.*;

public class MinAlternateDistance{
    public static void dfs(Map<Integer,Integer> conc, Map<Integer,Integer> asph, int[] res, int u, int v, int steps, int curr){
        if(curr == 1){
            conc.remove(u);
        }
        else{
            asph.remove(u);
        }
        
        res[v] = Math.min(res[v], steps);
        
        if(curr == 0){
            if(conc.containsKey(v)){
                dfs(conc,asph,res,v,conc.get(v),steps+1,1);
            }
            else{
                return;
            }
        }
        if(curr == 1){
            if(asph.containsKey(v)){
                dfs(conc,asph,res,v,asph.get(v),steps+1,0);
            }
            else{
                return;
            }
        }
    }
    public static void getShortestPath(Map<Integer,Integer> conc, Map<Integer,Integer> asph, int n){
        int[] res = new int[n];
        Arrays.fill(res,Integer.MAX_VALUE);
        res[0] = 0;
        if(conc.containsKey(0)){
            dfs(conc,asph,res,0,conc.get(0),1,1);
        }
        if(asph.containsKey(0)){
            dfs(conc,asph,res,0,asph.get(0),1,0);
        }
        for(int i : res){
            System.out.print((i == Integer.MAX_VALUE ? -1 : i) + " ");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int concrete = sc.nextInt();
        int asphalt = sc.nextInt();
        Map<Integer, Integer> conc = new HashMap<>();
        Map<Integer, Integer> asph = new HashMap<>();
        for(int i = 0;i<concrete;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            conc.put(u,v);
        }
        for(int i = 0;i<asphalt;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            asph.put(u,v);
        }
        getShortestPath(conc,asph,n);
        sc.close();
    }
}