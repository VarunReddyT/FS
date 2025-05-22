// Mr Robert is working with strings.
// He selected two strings S1 and S2, may differ in length,
// consists of lowercase alphabets only.

// Mr Robert has to update the strings S1, S2 to meet any one of the criteria:
// 	- All the alphabets in S1 should be less than all the alphabets in S2.
// 	- All the alphabets in S2 should be less than all the alphabets in S1.
// 	- Both S1 and S2 should have only one distinct alphabet in it.
// To Achieve, one of the criteria, you are allowed to replace any one letter in 
// the string with any other lowercase alphabet.

// Your task is to help Mr Robert, to find the minimum replacements to be done to 
// update the strings S1 and S2 to meet one of the criteria mentioned above.


// Input Format:
// -------------
// Two space separated strings S1 and S2.

// Output Format:
// --------------
// Print an integer, minimum number of replacements.


// Sample Input-1:
// ---------------
// apple ball

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// Consider the best way to make the criteria true:
// - Update S2 to "baaa" in 2 replacements, and Update S1 to "cpple" in 1 replacement
// then every alphabet in S2 is less than every alphabet in S1.
//         (OR)
// - Update S1 to "ppppp" in 3 replacements, then every alphabet in S2 is less 
// than every alphabet in S1.


// Sample Input-2:
// ---------------
// kmit kmec

// Sample Output-2:
// ----------------
// 2


import java.util.*;

public class MinStringReplacements{
    public static int minReplacements(String s1, String s2){
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        int n = s1.length();
        int m = s2.length();
        int res = Integer.MAX_VALUE;
        for(char c : s1.toCharArray()){
            freq1[c-'a']++;
        }
        for(char c : s2.toCharArray()){
            freq2[c-'a']++;
        }
        count1[0] = freq1[0];
        count2[0] = freq2[0];
        for (int i = 1; i < 26; i++) {
            count1[i] = count1[i - 1] + freq1[i];
            count2[i] = count2[i - 1] + freq2[i];
        }
        for (int i = 0; i < 25; i++) {
            int change1 = (n - count1[i]) + count2[i];
            int change2 = (m - count2[i]) + count1[i];
            res = Math.min(res, Math.min(change1, change2));
        }
        for (int i = 0; i < 26; i++) {
            int change3 = (n - freq1[i]) + (m - freq2[i]);
            res = Math.min(res, change3);
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(minReplacements(input[0], input[1]));
        sc.close();
    }
}