// 2. Palindrome Permutation
// Given a string, determine if a permutation of the string could form a palindrome.
// Example 1:
// Input: "code"
// Output: false
// Example 2:
// Input: "aab"
// Output: true
// Example 3:
// Input: "carerac"
// Output: true

import java.util.*;

public class PalindromePermutation {
    public static boolean canPermutePalindrome(String s) {
        int bitmask = 0;
        for(char c : s.toCharArray()) {
            bitmask ^= (1 << (c - 'a'));
        }
        return (bitmask & (bitmask - 1)) == 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(canPermutePalindrome(input));
        sc.close();
    }

}