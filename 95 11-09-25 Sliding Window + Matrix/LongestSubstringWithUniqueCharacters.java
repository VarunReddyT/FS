// Kiran is given a string S, and an integer N.
// Kiran wants to find the longest substring which has following properties:
// 	- the substring of S should be maximum in length, and 
// 	- should contains maximum of N unique characters in it.
// Your task is to help Kiran to find the longest substring 'ls' with 
// above properties and return the length of the substring 'ls'.

// Input Format:
// -------------
// Line-1: A string S
// Line-2: An integer N, number of distinct characters.

// Output Format:
// --------------
// Print an integer, length of longest substring with a max of N unique characters.


// Sample Input-1:
// ---------------
// philippines
// 3

// Sample Output-1:
// ----------------
// 6


// Sample Input-2:
// ---------------
// abaccdbcca
// 2

// Sample Output-2:
// ----------------
// 3

import java.util.*;

public class LongestSubstringWithUniqueCharacters{
    public static int getLongestSubstring(String s, int n){
        HashMap<Character,Integer> freq = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int low = 0;
        int high = 0;
        while(high < s.length()){
            while(freq.size() > n){
                freq.put(s.charAt(low),freq.get(s.charAt(low))-1);
                if(freq.get(s.charAt(low)) == 0){
                    freq.remove(s.charAt(low));
                }
                low++;
            }
            freq.put(s.charAt(high),freq.getOrDefault(s.charAt(high),0)+1);
            if(freq.size() == n){
                res = Math.max(res,high-low+1);
            }
            high++;
        }
        return res==Integer.MIN_VALUE ? 0 : res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        System.out.println(getLongestSubstring(s,n));
        sc.close();

    }
}