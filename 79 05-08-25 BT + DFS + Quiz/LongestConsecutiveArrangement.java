// A merchant has two types of idols, gold and silver.
// He has arranged the idols in the form of m*n grid, 
// 	- the gold idols are represented as 1's 
// 	- the silver idols are represented as 0's.

// Your task is to find the longest consecutive arrangement of gold idols, 
// The arrangement can be either horizontal, vertical, diagonal or 
// antidiagonal, but not the combination of these.


// Input Format:
// -------------
// Line-1: Two space separated integers m and n, grid size.
// Next m lines : n space separated integers, arrangement of idols.

// Output Format:
// --------------
// Print an integer, longest arranement of gold idols.


// Sample Input:
// ---------------
// 4 5
// 1 0 1 1 1
// 0 1 0 1 0
// 1 0 1 0 1
// 1 1 0 1 1

// Sample Output:
// ----------------
// 4


import java.util.*;

public class LongestConsecutiveArrangement{
    public static int dfs(int[][] arr, int i, int j, int n, int m, int row, int col){
        if(i<0 || j<0 || i>=n || j>=m || arr[i][j] == 0){
            return 0;
        }
        return 1 + dfs(arr,i+row,j+col,n,m,row,col);
    }
    public static int getLongestArrangement(int[][] arr, int n, int m){
        int res = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(arr[i][j] == 1){
                    res = Math.max(res,dfs(arr,i,j,n,m,0,1));
                    res = Math.max(res,dfs(arr,i,j,n,m,1,0));
                    res = Math.max(res,dfs(arr,i,j,n,m,1,-1));
                    res = Math.max(res,dfs(arr,i,j,n,m,1,1));
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(getLongestArrangement(arr,n,m));
        sc.close();
    }
}