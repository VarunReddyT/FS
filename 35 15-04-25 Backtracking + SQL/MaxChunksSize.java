// Captain Raynor is on a mission to decode an alien transmission.
// The transmission contains a single long string S, and it is believed to be 
// made up of multiple unique signal chunks, sent one after another with no spaces.

// Your task is to help Captain Raynor split the transmission into the maximum 
// number of non-empty, unique signal chunks such that when all chunks are 
// concatenated in order, they exactly recreate the original transmission S.

// Your goal is to maximize the number of unique chunks the message can be split into.

// Input Format:
// Line-1: A string S representing the alien transmission.

// Output Format:
// Print a single integer – the maximum number of distinct chunks the string can be split into.

// Sample Input-1:
// ---------------
// banana

// Sample Output-1:
// ----------------
// 4

// Explanation: 
// ------------
// One valid way to split the string is: "b", "a", "n", "ana".
// This keeps all chunks unique.
// Another way like "b", "a", "n", "an", "a" is invalid because "a" appears twice.


// Sample Input-2:
// ---------------
// mississippi

// Sample Output-2:
// ----------------
// 7

// Explanation: 
// ------------
// One valid way to split it is: "m", "i", "s", "si", "ssi", "p", "pi".
// All chunks are distinct and together recreate the original transmission.

// NOTE: Only contiguous chunks (i.e., substrings) are allowed. Subsequence-based 
//       splitting is not permitted.


import java.util.*;

public class MaxChunksSize{
    static int maxSize = 0;
    public static boolean isValid(HashSet<String> set, String s){
        return !set.contains(s);
    }
    public static void backtrack(String s, HashSet<String> set, int curr, int count){
        if(curr == s.length()){
            maxSize = Math.max(maxSize,count);
            return;
        }
        for(int i = curr+1;i<=s.length();i++){
            String sub = s.substring(curr,i);
            if(isValid(set,sub)){
                set.add(sub);
                backtrack(s,set,i,count+1);
                set.remove(sub);
            }
        }
    }
    public static int getMaxChunks(String s){
        maxSize = 0;
        HashSet<String> set = new HashSet<>();
        backtrack(s,set,0,0);
        return maxSize;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        getMaxChunks(s);
        System.out.println(getMaxChunks(s));
        sc.close();
    }
}