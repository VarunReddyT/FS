// You are working in a genetics laboratory where you are tasked with correcting 
// DNA sequences. Each DNA strand is represented as a sequence of characters 
// 'A', 'C', 'G', and 'T'.
// Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
// must be modified to match a targetDNA sequence exactly.

// You can perform the following mutation operations:
// - Insert a nucleotide (A, C, G, or T) into the DNA strand.
// - Delete a nucleotide from the DNA strand.
// - Replace a nucleotide with another one.

// Each operation counts as one step.

// Your task is to find the minimum number of mutation operations needed to 
// transform the originalDNA into the targetDNA.

// Input format:
// -------------
// 2 space seperated strings, originalDNA and targetDNA

// Output format:
// --------------
// An integer, the minimum number of mutation operations


// Example 1:
// -----------
// Input:
// ACGT AGT

// Output:
// 1

// Explanation:
// Delete 'C': "ACGT" → "AGT"
// Only 1 mutation is needed.

// Example 2:
// ----------
// Input:
// GATTAC GCATGCU

// Output:
// 4

// Explanation:
// - Replace 'A' with 'C': "GATTAC" → "GCTTAC"
// - Replace 'T' with 'A': "GCTTAC" → "GCATAC"
// - Replace 'A' with 'G': "GCATAC" → "GCATGC"
// - Insert 'U' at the end: "GCATGC" → "GCATGCU"

// Thus, 4 mutations are needed.AG


import java.util.*;

public class NucleotideOperations{
    // public static int getWays(String s1, String s2){
    //     int[][] dp = new int[s1.length()+1][s2.length()+1];
    //     for(int i = 0;i<=s1.length();i++){
    //         dp[i][0] = i; 
    //     }
    //     for(int i = 0;i<=s2.length();i++){
    //         dp[0][i] = i; 
    //     }
    //     for(int i = 1;i<=s1.length();i++){
    //         for(int j = 1;j<=s2.length();j++){
    //             if(s1.charAt(i-1) == s2.charAt(j-1)){
    //                 dp[i][j] = dp[i-1][j-1];
    //             }
    //             else{
    //                 dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
    //             }
    //         }
    //     }
    //     return dp[s1.length()][s2.length()];
    // }
    public static int helper(String s1, String s2, int[][] dp, int i, int j){
        if(i==s1.length()){
            return s2.length()-j;
        }
        else if(j == s2.length()){
            return s1.length()-i;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = helper(s1,s2,dp,i+1,j+1);
        }
        else{
            return dp[i][j] = 1 + Math.min(helper(s1,s2,dp,i+1,j+1),Math.min(helper(s1,s2,dp,i+1,j),helper(s1,s2,dp,i,j+1)));
        }
    }
    public static int getWays(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return helper(s1,s2,dp,0,0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String s1 = input[0];
        String s2 = input[1];
        System.out.println(getWays(s1,s2));
        sc.close();
    }
}