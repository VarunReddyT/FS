// 3. Remove All Ones with Row and Column Flips
// We are given an m x n binary matrix grid.
// In one operation, you can choose any row or column and flip each value in that rowor column
// (i.e., changing all 0's to 1's, and all 1's to 0's).
// Return true if it is possible to remove all 1‟s from the grid using any number ofoperations or false 
// otherwise.
// Example 1:
// Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
// Output: true
// Explanation: One possible way to remove all 1's from grid is to:
// - Flip the middle row
// - Flip the middle column
// Example 2:
// Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
// Output: false
// Explanation: It is impossible to remove all 1's from grid.
// Example 3:
// Input: grid = [[0]]
// Output: true
// Explanation: There are no 1's in grid.

import java.util.*;

public class RemoveAllOnesWithFlips {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int grid[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        System.out.println(removeOnes(grid));
        sc.close();
    }

    public static boolean removeOnes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int c = 0;c<n;c++){
            if(grid[0][c] == 1){
                for(int r = 0;r<m;r++){
                    grid[r][c] ^= 1;
                }
            }
        }
        for(int r = 1;r<m;r++){
            int sum = 0;
            for(int c = 0;c<n;c++){
                sum += grid[r][c];
            }
            if(sum != 0 && sum != n){
                return false;
            }  
        }
        return true;
    }
}