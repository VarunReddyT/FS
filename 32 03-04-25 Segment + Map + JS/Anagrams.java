// A detective is investigating a case involving a secret message hidden within a 
// longer note. The detective knows that the culprit rearranged the letters of a 
// short code-word into multiple secret locations within a larger note.

// Given two strings, note (the longer text) and codeWord (the short secret code), 
// your task is to help the detective find all starting positions within the note 
// where any rearrangement or shuffled of codeWord is located.

// Input Format:
// -------------
// Single line space separated strings, two words.

// Output Format:
// --------------
// Print the list of integers, indices.


// Sample Input-1:
// ---------------
// bacdgabcda abcd
 
// Sample Output-1:
// ----------------
// [0, 5, 6]

// Explanation:
// - At index 0: "bacd" is an anagram of "abcd"
// - At index 5: "abcd" matches exactly
// - At index 6: "bcda" is an anagram of "abcd"
// Thus, the positions [0, 5, 6] are returned.

// Sample Input-2:
// ---------------
// bacacbacdcab cab

// Sample Output-2:
// ----------------
// [0, 3, 4, 5, 9]

import java.util.*;

public class Anagrams{
    public static ArrayList<Integer> getIndices(String s, String code){
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Character,Integer> codeFreq = new HashMap<>();
        for(char c : code.toCharArray()){
            codeFreq.put(c,codeFreq.getOrDefault(c,0)+1);
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<code.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        if(map.equals(codeFreq)){
            res.add(0);
        }
        int left = 0;
        for(int right = code.length();right<s.length();right++){
            map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);
            map.put(s.charAt(left),map.get(s.charAt(left))-1);
            if(map.get(s.charAt(left))==0){
                map.remove(s.charAt(left));
            }
            if(map.equals(codeFreq)){
                res.add(left+1);
            }
            left++;
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String code = sc.next();
        System.out.println(getIndices(s,code));
        sc.close();
    }
}