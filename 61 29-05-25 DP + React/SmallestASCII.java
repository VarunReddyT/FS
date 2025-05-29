// Preethi is playing with strings. She is given 2 strings. 
// Preethi can remove any number of characters from 2 strings such that 2 strings 
// to be equal after removal (Sequence of characters shouldn't change).
// Find the smallest ASCII sum possible for the removed characters.

// Input Format:
// -------------
// Line-1: Two space seperated strings

// Output Format:
// --------------
// return an integer , represents ASCII sum with the given constraints.

// Sample Input-1:
// ---------------
// treat create

// Sample Output-1:
// ----------------
// 316

// Explanation:
// -------------
// Remove 't' in string1 and 'c' and 'e' in string 2. so sum= 116+99+101=316


// Sample Input-2:
// ---------------
// interest pintrest

// Sample Output-2:
// ----------------
// 213

// Explanation:
// -------------
// Remove 'e' in string1 and 'p' in string2. so sum=101+112=213

import java.util.*;

public class SmallestASCII{
    public static int solve(int[][] dp, String a, String b, int m, int n, int i, int j){
        if(i==a.length()){
            int sum = 0;
            for(int k = j;k<b.length();k++){
                sum += ((int)b.charAt(k));
            }
            return sum;
        }
        if(j==b.length()){
            int sum = 0;
            for(int k = i;k<a.length();k++){
                sum += ((int)a.charAt(k));
            }
            return sum;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(a.charAt(i) == b.charAt(j)){
            return dp[i][j] = solve(dp,a,b,m,n,i+1,j+1);
        }
        else{
            int onedel = ((int)a.charAt(i)) + solve(dp,a,b,m,n,i+1,j);
            int twodel = ((int)b.charAt(j)) + solve(dp,a,b,m,n,i,j+1);
            int bothdel = ((int)a.charAt(i)) + ((int)b.charAt(j)) + solve(dp,a,b,m,n,i+1,j+1);
            return dp[i][j] = Math.min(onedel,Math.min(twodel,bothdel));
        }
    }
    public static int getMaxAscii(String a, String b){
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return solve(dp,a,b,m,n,0,0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(getMaxAscii(input[0],input[1]));
        sc.close();
    }
}