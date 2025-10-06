// A city’s Smart Energy Controller must decide which power-saving plans to 
// activate in a single day. Each plan connects to certain zones of the city 
// and provides a specific energy-saving benefit (in kWh).

// However, each zone can only participate in one plan at a time (to avoid power overlap).

// You need to determine the maximum total energy saving that can be achieved by 
// activating a valid combination of plans without any zone being used more than once.

// Input Format
// ------------
// Line-1: Two space separated integers N M: Total number of city zones, number of plans.
// Next M lines: space separated integers of each plan, 
//       where first integer is Number of Zones in the plan, followed by zones, and
//       the last integer is energy-saving benefit.

// Output Format
// -------------
// Return a single integer — the maximum achievable total energy saving (kWh).


// Sample Input:
// -------------
// 4 4
// 2 1 2 100
// 2 2 3 200
// 2 3 4 150
// 2 1 4 120

// Sample Output:
// --------------
// 320

// Explanation:
// ------------
// We can activate Plan-2 (zones 2,3) and Plan-4 (zones 1,4).
// They do not overlap and give 200 + 120 = 320 kWh savings.
// Activating any other combination gives less total saving.

import java.util.*;

public class MaxEnergySaving{
    static int max = 0;
    public static void dfs(int[][] arr, int[] benefits, boolean[] visited, int n, int m, int idx, int curr){
        if(idx == arr.length){
            max = Math.max(max,curr);
            return;
        }
        dfs(arr,benefits,visited,n,m,idx+1,curr);
        boolean canUse = true;
        for(int z : arr[idx]){
            if (visited[z]) {
                canUse = false;
                break;
            }
        }
        if (canUse) {
            for(int z : arr[idx]){
                visited[z] = true;
            }
            dfs(arr, benefits, visited, n, m, idx + 1, curr + benefits[idx]);
            for (int z : arr[idx]){
                visited[z] = false;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[m][];
        int[] benefits = new int[m];
        for(int i = 0;i<m;i++){
            int zones = sc.nextInt();
            arr[i] = new int[zones];
            for(int j = 0;j<zones;j++){
                arr[i][j] = sc.nextInt();
            }
            benefits[i] = sc.nextInt();
        }
        boolean[] visited = new boolean[n+1];
        dfs(arr,benefits,visited,n,m,0,0);
        System.out.println(max);
        sc.close();
    }
}