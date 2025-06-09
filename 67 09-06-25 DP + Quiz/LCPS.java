// Alex and his twin brother Jordan often create secret messages. One day, Jordan 
// gives Alex two encrypted messages and challenges him to find the longest common 
// palindromic pattern hidden within both messages.

// Alex wants your help to decode the longest common palindromic subsequence that 
// exists in both strings.

// Your task is to determine the length of the longest subsequence that:
// - Appears in both messages
// - Is a palindrome

// Input Format:
// -------------
// input1: A string representing the first encrypted message.
// input2: A string representing the second encrypted message.

// Output Fromat:
// --------------
// Return an integer representing the length of the longest common palindromic 
// subsequence shared by both messages.


// Sample Input: 
// -------------
// adfa
// aagh

// Sample Output:
// --------------
// 2


// Sample Input-2:
// ---------------
// abcda
// fxaaba

// Sample Output:
// --------------
// 3

// Explanation:
// ------------
// The longest palindromic subsequence common to both is "aba" with length 3.

import java.util.*;

public class LCPS{
    public static boolean isPalindrome(String s){
        int low = 0;
        int high = s.length()-1;
        while(low<high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
    public static int helper(int[][] dp, int i, int j, String s){
        if(i>j){
            return 0;
        }
        if(i==j){
            return 1;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] = 2 + helper(dp,i+1,j-1,s);
        }
        else{
            return dp[i][j] = Math.max(helper(dp,i+1,j,s),helper(dp,i,j-1,s));
        }
    }
    public static int getPalindromeSubsequence(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return helper(dp,0,n-1,s);
    }
    public static int getLCPS(String a, String b){
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
        
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=m;j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        StringBuilder lcs = new StringBuilder();
        int i = n;
        int j = m;
        while(i>0 && j>0){
            if(a.charAt(i-1)==b.charAt(j-1)){
                lcs.append(a.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        String subseq = lcs.reverse().toString();
        if(isPalindrome(subseq)){
            return subseq.length();
        }
        return getPalindromeSubsequence(subseq);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        System.out.println(getLCPS(a,b));
        sc.close();
    }
}