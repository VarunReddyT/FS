// There are N cities, and M routes[], each route is a path between two cities.
// routes[i] = [city1, city2], there is a travel route between city1 and city2.
// Each city is numbered from 0 to N-1.
 
// There are one or more Regions formed among N cities. 
// A Region is formed in such way that you can travel between any two cities 
// in the region that are connected directly and indirectly.
 
// Your task is to findout the number of regions formed between N cities. 
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of cities and routes
// Next M lines: Two space separated integers city1, city2.
 
// Output Format:
// --------------
// Print an integer, number of regions formed.
 
 
// Sample Input-1:
// ---------------
// 5 4
// 0 1
// 0 2
// 1 2
// 3 4
 
// Sample Output-1:
// ----------------
// 2
 
 
// Sample Input-2:
// ---------------
// 5 6
// 0 1
// 0 2
// 2 3
// 1 2
// 1 4
// 2 4
 
// Sample Output-2:
// ----------------
// 1
 
// Note: Look HINT for explanation.


import java.util.*;

public class CityRegions{
    public static void initParent(int[] parent,int n){
        for(int i = 0;i<n;i++){
            parent[i] = i;
        }
    }
    public static void union(int[] parent, int[] rank, int x, int y){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX == rootY){
            return;
        }
        if(rank[rootX] > rank[rootY]){ 
            parent[rootY] = rootX;
        }
        else if(rank[rootY] > rank[rootX]){
            parent[rootX] = rootY;
        }
        else{
            parent[rootX] = rootY;
            rank[rootY]++;
        }
    }
    public static int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    public static int solve(int[][] grid, int n, int m){
        int[] parent = new int[n];
        initParent(parent,n);
        int[] rank = new int[n];
        for(int i = 0;i<m;i++){
            union(parent,rank,grid[i][0],grid[i][1]);
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<n;i++){
            set.add(find(parent,i));
        }
        return set.size();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[m][m]; 
        for(int i = 0;i<m;i++){
            for(int j = 0;j<2;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(solve(grid,n,m));
        sc.close();
    }
}