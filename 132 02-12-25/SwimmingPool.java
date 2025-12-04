/*Few people are living in a township of size N*N, where each 1*1 part 
of the township is either a villa or a swimming pool. 
You are given the structure of township as a 2d matrix of size N*N, 
filled with 0's and 1's, where '0'-indicates a swimming pool and 
'1'- indiactes a villa. 

Your task is to find a swimming pool, such that its distance to 
the nearest villa(s) is maximized, and return the distance.

If the township contains only the villas or only swimming pools, print '-1'. 

The distance used in this problem is the Manhattan distance: 
the distance between two cells (a0, b0) and (a1, b1) is |a0 - a1| + |b0 - b1|


Input Format:
-------------
Line-1: An integer N, size of the 2d matrix.
Next N lines: N space separated integers, matrix[][] either 0 or 1.

Output Format:
--------------
An integer, maximum distance.


Sample Input-1:
---------------
4
1 0 1 1
0 0 0 0
1 0 1 0
1 0 0 1

Sample Output-1:
----------------
2

Explanation: 
------------
The swimming pool at (1, 1) is with distance 2 from the nearest villas.


Sample Input-2:
---------------
4
1 0 0 0
0 0 0 0
1 0 0 0
0 1 0 1

Sample Output-2:
----------------
3

Explanation: 
------------
The swimming pool at (0,3) or (1, 2) are with distance 3 from the nearest villas.
 */
import java.util.*;
import java.util.LinkedList;

public class SwimmingPool{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = cin.nextInt();
            }
        }
        System.out.println(find(arr,n));
        cin.close();
    }
    static int find(int arr[][], int n){
        /*
            1) initialize the res = MIN( we need to maximise this )
            2) initialize the dp[n][n] to MAX_VALUE, it is used to store value of 0's distance to nearest 1
        */
        int res = Integer.MIN_VALUE;
        int dp[][] = new int[n][n];
        for(int i = 0; i < n ; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (arr[i][j] == 0){
                    Set<String> set = new HashSet<>();
                    int val = bfs(i,j,n,arr,set);
                    if(val==Integer.MAX_VALUE){
                        return -1;
                    }
                    res = Math.max(res,val);
                }
            }
        }

        return res==Integer.MIN_VALUE? -1 : res;
    }
    static int bfs(int i, int j, int n, int arr[][], Set<String> set ){
        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{i,j});
            int dir[][] = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
            };
        set.add(i+"-"+j);
        while(!q.isEmpty()){
            int t[] = q.poll();
            if(arr[t[0]][t[1]]==1){
                return Math.abs(t[0]-i) + Math.abs(t[1]-j);
            }
            for (int d[]: dir){
                int nr = d[0] + t[0];
                int nc = d[1] + t[1];
                if(nr<0|| nc<0 || nr>=n || nc>=n || set.contains(nr+"-"+nc)){
                    continue;
                }
                set.add(nr+"-"+nc);
                q.add(new int[]{nr,nc});
            }
        }
        return Integer.MAX_VALUE;
    }
}