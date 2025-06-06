// You have given flatland in the form of m*n grid,
// The land contains some ponds represented as 0,
// and the land represented as 1.

// Your task is to find the number of square-shaped lands which contains no pond. 


// Input Format:
// -------------
// Line-1: Two integers M and N.
// Next M lines: N space separated integers.

// Output Format:
// --------------
// Print an integer, number of square-grids.


// Sample Input-1:
// ---------------
// 3 4
// 0 1 1 1
// 1 1 1 1
// 0 1 1 1

// Sample Output-1:
// ----------------
// 15

// Explanation:
// ------------
// There are 10 square-grids of side length 1.
// There are 4 square-grids of side length 2.
// There is 1 square-grid of side length 3.

// Total number of square-grids without a pond in it = 10 + 4 + 1 = 15.


// Sample Input-2:
// ---------------
// 3 3
// 1 0 1
// 1 1 0
// 1 1 0

// Sample Output-2:
// ----------------
// 7

// Explanation:
// ------------
// There are 6 square-grids of side length 1.
// There is 1 square-grid of side length 2.
// Total number of square-grids without a pond in it = 6 + 1 = 7.

import java.util.*;
public class CountSquares{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int dp[][] = new int[m+1][n+1];
        int sum = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(arr[i-1][j-1]==1) dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                sum+=dp[i][j];
            }
        }
        System.out.println(sum);
        sc.close();
    }
}