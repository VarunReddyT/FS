// Arjun wants to build some homes in a land of size R*C.
// He wanted to construct homes in rectangular shape.
// The place which is remained will be used for gradening.
// Accordingly he has prepared the plan and given as
// an 2d array plan[][], where 1 indicates home, and 0 indicates garden area.

// A home is set of cells with value 1 in rectangular shape.
// He wants to findout all the homes in the plan and store their co-ordinates in 
// the following order, coords[i] = [x1,y1,x2,y2], where (x1,y1) is the starting
// co-ordinate (top left corner), and (x2,y2) is the ending co-ordinate 
// (bottom right corner) of i-th home.

// Your task is to help Arjun to find all the homes and return the coords[][] of 
// all the homes from top left corner to bottom right corner.

// NOTE: No two homes are adjacent to each other in 4 directions,
// (left, right, top, bottom).

// Input Format:
// -------------
// Line-1: Two integers R and C, size of the land.
// Next R lines: C space separated integers, either 0 or 1
// 0- represents garden area land and 1- represents the home.

// Output Format:
// --------------
// Print 2d array, the co-ordinates of all homes.


// Sample Input-1:
// ---------------
// 2 3
// 1 0 0
// 0 1 1
 
// Sample Output-1:
// ----------------
// [0, 0, 0, 0][1, 1, 1, 2]


// Sample Input-2:
// ---------------
// 4 4
// 1 1 0 1
// 0 0 0 0
// 1 1 0 1
// 1 1 0 1
 
// Sample Output-2:
// ----------------
// [0, 0, 0, 1][0, 3, 0, 3][2, 0, 3, 1][2, 3, 3, 3]


import java.util.*;
import java.util.LinkedList;

public class HomeCoordinates {
    public static List<Integer> bfs(int[][] arr, int n, int m, int i, int j){
        List<Integer> home = new ArrayList<>();
        home.add(i);
        home.add(j);
        arr[i][j] = 0;
        int[] neighRow = {-1,0,1,0};
        int[] neighCol = {0,1,0,-1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i,j});
        int temp1 = i;
        int temp2 = j;
        while(!q.isEmpty()){
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            for(int k = 0;k<4;k++){
                int nr = r + neighRow[k];
                int nc = c + neighCol[k];
                if(nr>=0 && nr<n && nc>=0 && nc<m && arr[nr][nc] == 1){
                    arr[nr][nc] = 0;
                    temp1 = Math.max(temp1,nr);
                    temp2 = Math.max(temp2,nc);
                    q.offer(new int[]{nr,nc});
                }
            }
        }
        home.add(temp1);
        home.add(temp2);
        return home;
    }
    public static List<List<Integer>> getHomes(int[][] arr, int n, int m){
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(arr[i][j] == 1){
                    res.add(bfs(arr,n,m,i,j));
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
        List<List<Integer>> res = getHomes(arr,n,m);
        for(List<Integer> i : res){
            System.out.print(i);
        }
        sc.close();
    }
}