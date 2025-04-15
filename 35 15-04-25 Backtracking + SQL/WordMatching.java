// Given two strings S1 and S2, find if S2 can match S1 or not.

// A match that is both one-to-one (an injection) and onto (a surjection), 
// i.e. a function which relates each letter in string S1 to a separate and 
// distinct non-empty substring in S2, where each non-empty substring in S2
// also has a corresponding letter in S1.

// Return true,if S2 can match S1.
// Otherwise false.

// Input Format:
// -------------
// Line-1 -> Two strings S1 and S2

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// abab kmitngitkmitngit

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// aaaa kmjckmjckmjckmjc

// Sample Output-2:
// ----------------
// true

// Sample Input-3:
// ---------------
// mmnn pqrxyzpqrxyz

// Sample Output-3:
// ----------------
// false

import java.util.*;

public class WordMatching{
    public static boolean backtrack(String s1, String s2, int index1, int index2, HashMap<Character,String> map, HashSet<String> set){
        if(index1 == s1.length() && index2== s2.length()){
            return true;
        }
        if(index1 == s1.length() || index2 == s2.length()){
            return false;
        }
        char c = s1.charAt(index1);
        if(map.containsKey(c)){
            String curr = map.get(c);
            if(!s2.startsWith(curr,index2)){
                return false;
            }
            return backtrack(s1,s2,index1+1,index2 + curr.length(),map, set);
        }
        for(int i = index2 + 1; i<=s2.length();i++){
            String sub = s2.substring(index2,i);
            if(set.contains(sub)){
                continue;
            }
            map.put(c,sub);
            set.add(sub);
            
            if(backtrack(s1,s2,index1+1,i,map,set)){
                return true;
            }
            
            map.remove(c);
            set.remove(sub);
        }
        return false;
    }
    public static boolean isMatching(String s1, String s2){
        HashMap<Character,String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        return backtrack(s1,s2,0,0,map,set);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(isMatching(input[0],input[1]));
        sc.close();
    }
}