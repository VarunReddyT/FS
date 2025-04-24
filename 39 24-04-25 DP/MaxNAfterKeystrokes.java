// Amogh is an Antiquarian, The person who collects antiques.
// He found a rear keyboard which has following keys,
// Keys are 'N', 'S', 'C' and 'P'

// 1st Key - 'N': Print one character 'N' on Console.
// 2nd Key - 'S': Select the whole Console.
// 3rd Key - 'C': Copy selected content to buffer.
// 4th Key - 'P': Print the buffer on Console, and append it after what has 
// already been printed.

// Now, your task is to find out maximum numbers of 'N's you can print
// after K keystrokes . 

// Input Format:
// -------------
// An integer K

// Output Format:
// --------------
// Print an integer, maximum numbers of 'N's you can print.


// Sample Input-1:
// -------------------
// 3

// Sample Output-1:
// -------------------- 
// 3

// Explanation: 
// ---------------
// We can print at most get 3 N's on console by pressing following key sequence:
// N, N, N



// Sample Input-2:
// -------------------
// 7

// Sample Output-2:
// ---------------------
// 9

// Explanation: 
// ---------------
// We can print at most get 9 N's on console by pressing following key sequence:
// N, N, N, S, C, P, P


import java.util.*;

public class MaxNAfterKeystrokes{
    public static int helper(int n, int[] dp){
        if(n<=6){
            return n;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        int count = 0;
        for(int i = n-3;i>=1;i--){
            int curr = helper(i,dp)*(n-i-1);
            count = Math.max(curr,count);
        }
        return dp[n] = count;
    }
    public static int getWays(int n){
        if(n<=6){
            return n;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return helper(n,dp);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getWays(n));
        sc.close();
    }
}