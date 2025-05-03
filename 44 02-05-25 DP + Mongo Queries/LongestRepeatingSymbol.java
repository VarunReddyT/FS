/*
Archaeologists discovered an ancient manuscript represented by a string 'text',
where each character represents an ancient symbol. They suspect the manuscript
might contain repeated symbol patterns (substrings).

Your task is to analyze the text and determine the length of the longest
repeating symbol pattern. If the text contains no repeating patterns, return '0'.

Example:
--------
Input=
scarabankhscarab

Output=
6

Explanation: The longest repeating symbol pattern is "scarab", appearing twice.

 Constraints:
- 1 <= text.length <= 2000
- 'text' consists of lowercase English letters ('a' - 'z').

 */

import java.util.*;

public class LongestRepeatingSymbol{
    public static int getRepeatingSubtring(String s, int n){
        int[][] dp = new int[n+1][n+1];
        int maxLen = 0;
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                if(s.charAt(i-1) == s.charAt(j-1) && i!=j){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = 0;
                }
                maxLen = Math.max(maxLen,dp[i][j]);
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int n = text.length();
        System.out.println(getRepeatingSubtring(text, n));
        sc.close();
    }
}