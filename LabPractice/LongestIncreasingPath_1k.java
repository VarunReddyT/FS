// k) Write a JAVA Program to find the Longest Increasing Path in a Matrix.
// Given an integer matrix, find the length of the longest increasing path. From each cell, you can either 
// move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the 
// boundary (i.e. wrap-around is not allowed).
// Example 1:
// Input: nums = 3 3
// 9 9 4
// 6 6 8
// 2 1 1
// Output: 4
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:
// Input: nums =3 3
// 3 4 5
// 3 2 6
// 2 2 1
// Output: 4
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

import java.util.*;

public class LongestIncreasingPath_1k {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static int longestIncreasingPath(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int longest = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                longest = Math.max(longest, dfs(matrix, dp, i, j));
            }
        }
        return longest;
    }
    public static int dfs(int[][] matrix, int[][] dp, int row, int col){
        if(dp[row][col] > 0){
            return dp[row][col];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int currLongest = 0;

        for(int c = 0;c<4;c++){
            int i = row + dx[c];
            int j = col + dy[c];
            if(i >= 0 && i < m && j >= 0 && j < n && matrix[i][j] > matrix[row][col]){
                currLongest = Math.max(currLongest, dfs(matrix, dp, i, j));
            }
        }
        dp[row][col] = currLongest + 1;
        return dp[row][col];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int matrix[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();
        System.out.println(longestIncreasingPath(matrix));
        sc.close();
    }
}