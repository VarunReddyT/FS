// Given a classic mobile phone, and the key pad of the phone looks like below.
// 	1		2		3
// 		   abc	   def
		 
// 	4		5		6
//    ghi     jkl     mno
  
// 	7		8		9
//   pqrs     tuv    wxyz
	
// 	*		0		#


// You are given a string S contains digits between [2-9] only, 
// For example: S = "2", then the possible words are "a", "b", "c".

// Now your task is to find all possible words that the string S could represent.
// and print them in a lexicographical order. 

// Input Format:
// -------------
// A string S, consist of digits [2-9]

// Output Format:
// --------------
// Print the list of words in lexicographical order.


// Sample Input-1:
// ---------------
// 2

// Sample Output-1:
// ----------------
// [a, b, c]


// Sample Input-2:
// ---------------
// 24

// Sample Output-2:
// ----------------
// [ag, ah, ai, bg, bh, bi, cg, ch, ci]


import java.util.*;

public class LetterCombinations{
    public static void backtrack(StringBuilder sb, String s, ArrayList<String> res, HashMap<Integer,ArrayList<Character>> map, int curr){
        if(curr == s.length()){
            res.add(sb.toString());
            return;
        }
        ArrayList<Character> letters = map.get(s.charAt(curr)-'0');
        for(int i = 0;i<letters.size();i++){
            sb.append(letters.get(i));
            backtrack(sb,s,res,map,curr+1);
            sb.deleteCharAt(sb.length()-1);
        }
        
    }
    public static ArrayList<String> getCombinations(String s){
        HashMap<Integer,ArrayList<Character>> map = new HashMap<>();
        map.put(2,new ArrayList<Character>(Arrays.asList('a','b','c')));
        map.put(3,new ArrayList<Character>(Arrays.asList('d','e','f')));
        map.put(4,new ArrayList<Character>(Arrays.asList('g','h','i')));
        map.put(5,new ArrayList<Character>(Arrays.asList('j','k','l')));
        map.put(6,new ArrayList<Character>(Arrays.asList('m','n','o')));
        map.put(7,new ArrayList<Character>(Arrays.asList('p','q','r','s')));
        map.put(8,new ArrayList<Character>(Arrays.asList('t','u','v')));
        map.put(9,new ArrayList<Character>(Arrays.asList('w','x','y','z')));
        
        ArrayList<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        backtrack(sb,s,res,map,0);
        
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(getCombinations(s));
        sc.close();
    }
}