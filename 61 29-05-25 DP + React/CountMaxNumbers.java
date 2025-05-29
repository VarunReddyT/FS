// Mr Shravan is playing a Match-the-Pair Game.
// Initially, He is given two teams team1 and team2.
// Each team has some persons in it, each person is identified with an ID,
// ID's are positive numbers, duplicates are allowed.

// Your task is to match the persons from team1 to team2.
// Team1 consist of M persons and Team2 consist of N persons.
// And the rules for matching the persons are as follows:
// 	- You are allowed to match the persons from team1 with team2,
// 	only if the IDs are equal and you have to draw a line between the pair.
// 	- You are not allowed draw the line which is passing through another line.
// 	- If you are not allowed to draw the line, you cannot match the pair.
// 	- Each person must connected with atmost one person only.

// Finally, count the maximum numbers of pairs can be formed between the teams.


// Input Format:
// -------------
// Line-1: Two space separated integers, M and N.
// Line-2: M space separated integers, team1[].
// Line-3: N space separated integers, team2[]. 

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 4 4
// 1 2 3 5
// 3 1 5 2

// Sample Output-1:
// ----------------
// 2

// Explanation: 
// ------------
// You can Match the pair as follows: 
// S1 - team1[0]-team2[1], team1[1]-team2[3]
// S2 - team1[0]-team2[1], team1[3]-team2[2]
// S3 - team1[2]-team2[0], team1[3]-team2[2]
// In S1 you cannot match 3 with 3 or 5 with 5, because you are
// not allowed to draw a line. same for S2 and S3.


// Sample Input-2:
// ---------------
// 6 7
// 6 5 2 4 2 5
// 2 5 3 4 6 2 4

// Sample Output-2:
// ----------------
// 3

import java.util.*;

public class CountMaxNumbers{
    public static int solve(int[][] dp, int[] arr1, int[] arr2, int m, int n, int i, int j){
        if(i>=m || j>=n){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(arr1[i] == arr2[j]){
            return dp[i][j] = 1 + solve(dp,arr1,arr2,m,n,i+1,j+1);
        }
        else{
            return dp[i][j] = Math.max(solve(dp,arr1,arr2,m,n,i+1,j),solve(dp,arr1,arr2,m,n,i,j+1));
        }
    }
    public static int getMaxPairs(int[] arr1, int[] arr2, int m, int n){
        int[][] dp = new int[m+1][n+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return solve(dp,arr1,arr2,m,n,0,0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] arr1 = new int[m];
        int[] arr2 = new int[n];
        for(int i = 0;i<m;i++){
            arr1[i] = sc.nextInt();
        }
        for(int i = 0;i<n;i++){
            arr2[i] = sc.nextInt();
        }
        System.out.println(getMaxPairs(arr1,arr2,m,n));
        sc.close();
    }
}