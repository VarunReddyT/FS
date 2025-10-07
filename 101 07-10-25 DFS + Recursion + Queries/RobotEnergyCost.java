// A warehouse is organized as a grid of cells (N x M), where each cell represents 
// a location with a certain energy cost for the robot to move through.
// The robot starts at the top-left corner (0, 0) and must reach the bottom-right 
// corner (N-1, M-1).

// The robot can move only:
// ➡️ Right (→)
// ⬇️ Down (↓)

// However:
// The robot’s battery can only store up to E energy units before it must recharge.
// A recharge adds a penalty cost R to the total energy consumption.
// The robot can choose when to recharge (after any move) if its next move would 
// exceed the battery limit.

// Find the minimum total energy cost (including recharge penalties) required for 
// the robot to reach its destination.


// Input Format:
// -------------
// N M E R
// <energy grid of size N x M>

// Output Format:
// --------------
// Minimum total energy cost to reach destination


// Sample Input:
// -------------
// 3 3 6 5
// 1 3 2
// 4 2 1
// 3 1 2

// Sample Output:
// -------------
// 14


// Explanation:
// ------------
// Let’s examine different paths and their costs.

// Path 1: Right → Right → Down → Down
// Cells: (0,0) → (0,1) → (0,2) → (1,2) → (2,2)
// Cost path: 1 + 3 + 2 + 1 + 2 = 9
// Battery limit = 6 → must recharge once after crossing (0,2).
// Total cost = 9 + 5 = 14


// Path 2: Down → Down → Right → Right
// Cells: (0,0) → (1,0) → (2,0) → (2,1) → (2,2)
// Cost: 1 + 4 + 3 + 1 + 2 = 11
// Battery limit = 6 → need recharge after (1,0).
// Total = 11 + 5 = 16

// There might be more paths, The minimum one is 14


import java.util.*;

public class RobotEnergyCost{
    public static void dfs(int[][] grid, int n, int m, int e, int r, int i, int j, int currCost, int[] cost, int currEnergy){
        if(i<0 || i>=n || j<0 || j>=m){
            return;
        }
        if(currEnergy < grid[i][j]){
            currCost += r;
            currEnergy = e;
        }
        currCost += grid[i][j];
        currEnergy -= grid[i][j];
        if(i == n-1 && j == m-1){
            cost[0] = Math.min(cost[0], currCost);
            return;
        }
        dfs(grid,n,m,e,r,i+1,j,currCost,cost,currEnergy); 
        dfs(grid,n,m,e,r,i,j+1,currCost,cost,currEnergy); 
    }
    public static int getMinCost(int[][] grid, int n, int m, int e, int r){
        int[] cost = new int[]{Integer.MAX_VALUE};
        dfs(grid,n,m,e,r,0,0,0,cost,e);
        return cost[0];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int e = sc.nextInt();
        int r = sc.nextInt();
        int grid[][] = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0;j < m; j++){
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(getMinCost(grid,n,m,e,r));
        sc.close();
    }
}