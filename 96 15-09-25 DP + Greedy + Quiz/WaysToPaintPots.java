// Hanu is a potter, he has prepared N Clay-Pots, put them in a line.
// He decided to paint the clay-pots with k colors.
// He has to paint all the clay-pots such that,
// no more than two adjacent clay-pots have the same color.

// Your task is to help Hanu, to find the total number of ways,
// he can paint the clay-pots.

// Input Format:
// -------------
// Two integers N and K, number of clay-pots, and number of colors.

// Output Format:
// --------------
// Print an integer, Number of ways.


// Sample Input:
// ---------------
// 3 2

// Sample Output:
// ----------------
// 6

// Explanation:
// ------------
// pots		pot-1	pot-2	pot-3
// ----- 	----- 	----- 	-----
// 1 			c1 		c1 		c2
// 2 			c1 		c2 		c1
// 3 			c1 		c2 		c2
// 4 			c2 		c1 		c1
// 5 			c2 		c1 		c2
// 6 			c2 		c2 		c1


import java.util.*;

public class WaysToPaintPots{
    public static int solve(int[][] dp, int index, int previousCase, int n, int k){
        if(index == n-1){
            return 1;
        }
        if(dp[index][previousCase] != -1){
            return dp[index][previousCase];
        }
        int ways = 0;
        // Case 1 : If previous two colours are same
        if(previousCase == 1){
            ways = (k-1)*solve(dp,index+1,0,n,k);
        }
        // Case 2 : Previous two colors are not same - so either choose the same colour or different
        else{
            ways = solve(dp,index+1,1,n,k) + (k-1)*solve(dp,index+1,0,n,k);
        }
        return dp[index][previousCase] = ways;
    }
    public static int getColourCombinations(int n, int k){
        int[][] dp = new int[n][2];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return k*solve(dp,0,0,n,k);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(getColourCombinations(n,k));
        sc.close();
    }
}