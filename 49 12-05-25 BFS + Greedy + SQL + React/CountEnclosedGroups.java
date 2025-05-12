// Pranav has a puzzle board filled with square boxes in the form of a grid.
// Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

// Pranav wants to find out the number of empty spaces which are completely 
// surrounded by the square boxes (left, right, top, bottom) in the board.

// You are given the board in the form of a grid M*N, filled wth 0's and 1's.
// Your task is to help Pranav to find the number of empty groups surrounded by
// the boxes in the puzzle board.

// Input Format:
// -------------
// Line-1: Two integers M and N, the number of rows and columns in the board.
// Next M lines: contains N space-separated either 0 or 1.

// Output Format:
// --------------
// Print an integer, the number of empty spaces in the puzzle board.


// Sample Input-1:
// ---------------
// 6 7
// 1 1 1 1 0 0 1
// 1 0 0 0 1 1 0
// 1 0 0 0 1 1 0
// 0 1 1 1 0 1 0
// 1 1 1 0 0 1 1
// 1 1 1 1 1 1 1

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// The 2 empty groups are as follows:
// 1st group starts at cell(1,1), 2nd group starts at cell(3,4).
// The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
// are not valid empty groups, because they are not completely surrounded by boxes.


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
// 1

// Explanation:
// ------------
// The only empty group starts at cell(1,1) is surrounded by boxes.


import java.util.*;
import java.util.LinkedList;

public class CountEnclosedGroups {
    public static int bfs(int[][] arr, int n, int m, int i, int j){
        if(i == 0 || j == 0 || i == n-1 || j == m-1){
            return 0;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        int[] neighRow = {-1,0,1,0};
        int[] neighCol = {0,1,0,-1};
        arr[i][j] = 1;
        boolean check = true;
        while(!q.isEmpty()){
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            if(r == 0 || c == 0 || r == n-1 || c == m-1){
                // return 0;

                // Can't directly return 0 here, because 
                // we need to check all the neighbours of this cell
                // to see if they are also 0 or not and mark them as visited
                
                check = false;
            }
            for(int x = 0;x<4;x++){
                int nr = r + neighRow[x];
                int nc = c + neighCol[x];
                if(nr>=0 && nr<n && nc>=0 && nc<m && arr[nr][nc] == 0){
                    q.offer(new int[]{nr,nc});
                    arr[nr][nc] = 1;
                }
            }
        }
        return check ? 1 : 0;
    }
    public static int getGroups(int[][] arr, int n, int m){
        int count = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(arr[i][j] == 0){
                    count += bfs(arr,n,m,i,j);
                }
            }
        }
        return count;
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
        System.out.println(getGroups(arr,n,m));
        sc.close();
    }
}