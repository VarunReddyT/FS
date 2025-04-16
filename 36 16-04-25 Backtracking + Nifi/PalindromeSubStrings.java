// In the ancient land of Palindoria, wizards write magical spells as strings of 
// lowercase letters. However, for a spell to be effective, each segment of the 
// spell must read the same forward and backward.

// Given a magical spell 'spell', your task is to partition it into sequences of 
// valid magical spell segments. 
// Your goal is to help the wizard discover all valid combinations of magical spell 
// segmentations.

// Example 1:
// ----------
// Input:  
// aab
  
// Output:  
// [[a, a, b], [aa, b]]

// Example 2:

// Input:  
// a  
// Output:  
// [[a]]

// Spell Constraints:

// - The length of the spell is between 1 and 16 characters.  
// - The spell contains only lowercase English letters.  

import java.util.*;

public class PalindromeSubStrings{
    public static boolean isPalindrome(String s){
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }
    public static void backtrack(String s, List<List<String>> res, int index, ArrayList<String> temp){
        if(index == s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index+1; i<=s.length();i++){
            String subs = s.substring(index,i);
            if(isPalindrome(subs)){
                temp.add(subs);
                backtrack(s,res,i,temp);
                temp.remove(temp.size()-1);
            }
        }
        
    }
    public static List<List<String>> getCombinations(String s){
        List<List<String>> res = new ArrayList<>();
        backtrack(s,res,0,new ArrayList<>());
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getCombinations(s));
        sc.close();
    }
}