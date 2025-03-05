// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.


// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.


// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6


// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4


import java.util.*;

public class Computers{
    public static void initParent(int[] parent,int n){
        for(int i = 0;i<=n;i++){
            parent[i] = i;
        }
    }
    public static void union(int[] parent,int[] arr, int[] rank, int x, int y){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX == rootY){
            if(arr[0] != -1){
                parent[arr[1]] = arr[0];
                parent[arr[2]] = arr[0];
            }
            arr[0] = rootX;
            arr[1] = x;
            arr[2] = y;
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
    public static void solve(int[][] grid, int n){
        int[] parent = new int[n+1];
        initParent(parent,n);
        int[] rank = new int[n+1];
        int[] arr = new int[3];
        for(int i = 0;i<n;i++){
            union(parent,arr,rank,grid[i][0],grid[i][1]);
        }
        System.out.println(arr[1] + " " + arr[2]);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][2];
        for(int i = 0;i<n;i++){
            grid[i][0] = sc.nextInt();
            grid[i][1] = sc.nextInt();
        }
        solve(grid,n);
        sc.close();
    }
}