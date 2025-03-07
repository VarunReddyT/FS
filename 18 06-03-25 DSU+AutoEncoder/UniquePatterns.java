// Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
// cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// The puzzle board has some patterns formed with boxes in it, 
// the patterns may be repeated. The patterns are formed with boxes (1's) only, 
// that are connected horizontally and vertically but not diagonally.

// Pranav wants to find out the number of unique patterns in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of unique patterns in 
// the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the grid-land.
// Next M lines: contains N space-separated integers [0, 1].

// Output Format:
// --------------
// Print an integer, the number of unique patterns in the puzzle board.


// Sample Input-1:
// ---------------
// 5 5
// 0 1 0 1 1
// 1 1 1 0 1
// 0 1 0 1 0
// 1 0 1 1 1
// 1 1 0 1 0

// Sample Output-1:
// ----------------
// 3

// Explanation-1:
// ------------
// The unique patterns are as follows:
//   1			1 1	    1 
// 1 1 1		  1 ,	1 1
//   1	   ,	
   
   
// Sample Input-2:
// ---------------
// 6 6
// 1 1 0 0 1 1
// 1 0 1 1 0 1
// 0 1 0 1 0 0
// 1 1 0 0 0 1
// 0 0 1 0 1 1
// 1 1 0 1 0 0

// Sample Output-2:
// ----------------
// 5

// Explanation-2:
// ------------
// The unique patterns are as follows:
// 1 1		1 1		    1		1 1	,	1
// 1   ,     1 ,	    1 1 ,		


import java.util.*;
import java.util.LinkedList;

public class UniquePatterns{
    public static String bfs(int[][] grid, int n, int m, Queue<int[]> queue){
        StringBuilder sb = new StringBuilder();
        int[] neighRow = {-1,0,1,0};
        int[] neighCol = {0,1,0,-1};
        char[] direction = {'l','t','r','d'};
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int r = top[0];
            int c = top[1];
            for(int i = 0;i<4;i++){
                int nr = r + neighRow[i];
                int nc = c + neighCol[i];
                if(nr>=0 && nr<n && nc>=0 && nc<m && grid[nr][nc]==1){
                    queue.offer(new int[]{nr,nc});
                    sb.append(direction[i]);
                    grid[nr][nc] = 0;
                }
                else{
                    sb.append('b');
                }
            }
        }
        return sb.toString();
    }
    public static int getUniquePatterns(int[][] grid, int n, int m){
        Set<String> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == 1){
                    queue.offer(new int[]{i,j});
                    grid[i][j] = 0;
                    set.add(bfs(grid,n,m,queue));
                }
            }
        }
        return set.size();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(getUniquePatterns(grid,n,m));
        sc.close();
    }
}