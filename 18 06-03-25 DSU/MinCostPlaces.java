// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.
 
// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.
  
// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.
 
 
// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5
 
// Sample Output-1:
// ----------------
// 8
 
// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4
 
// Sample Output-2:
// ----------------
// -1

import java.util.*;

public class MinCostPlaces{
    public static void initParent(int[] parent,int n){
        for(int i = 0;i<=n;i++){
            parent[i] = i;
        }
    }
    public static boolean union(int[] parent, int[] rank, int x, int y, int count){
        int rootX = find(parent,x);
        int rootY = find(parent,y);
        if(rootX == rootY){
            return false;
        }
        if(rank[rootX]>rank[rootY]){
            parent[rootY] = rootX;
        }
        else if(rank[rootX]<rank[rootY]){
            parent[rootX] = rootY;
        }
        else{
            parent[rootX] = rootY;
            rank[rootY]++;
        }
        return true;
    }
    public static int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent,parent[x]);
        }
        return parent[x];
    }
    public static int findMinimumCost(ArrayList<int[]> grid, int n, int m){
        int cost = 0;
        int[] parent = new int[m+1];
        int[] rank = new int[m+1];
        initParent(parent,m);
        int count = 0;
        Collections.sort(grid,(a,b)->a[2]-b[2]);
        for(int[] arr : grid){
            if(union(parent,rank,arr[0],arr[1],count)){
                cost += arr[2];
                count++;
            }
            if(count == n-1){
                return cost;
            }
        }
        return -1;
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<int[]> grid = new ArrayList<>();
        for(int i=0; i<m; i++){
            grid.add(new int[]{sc.nextInt(),sc.nextInt(),sc.nextInt()});
        }
        System.out.println(findMinimumCost(grid,n,m));
        sc.close();
    }
}