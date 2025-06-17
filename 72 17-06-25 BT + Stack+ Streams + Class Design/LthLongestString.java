// Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
// make strings such that no two adjacents alphabets are same.

// For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
// are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

// Given two integers N and L, Ravi made a list of all acceptable strings of 
// length N sorted in lexicographical order.

// Return the Lth string of this list or return an empty string if there are less 
// than L acceptable strings of length N.

// Input Format:
// -------------
// Line-1: Two space separated integers N and L.

// Output Format:
// --------------
// Print a string result.


// Sample Input-1:
// ---------------
// 2 3

// Sample Output-1:
// ----------------
// ba

// Explanation:
// -------------
// Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


// Sample Input-2:
// ---------------
// 3 4

// Sample Output-2:
// ----------------
// acb

// Explanation:
// ------------
// Strings in lexigraphical order are aba,abc,aca,acb,bab....


import java.util.*;

public class LthLongestString{
    public static void backtrack(List<String> res, String[] letters, StringBuilder sb, int len, int l){
        if(res.size() == l){
            return;
        }
        if(sb.length() > 1 && (sb.charAt(sb.length()-1) == sb.charAt(sb.length()-2))){
            return;
        }
        if(sb.length()==len){
            res.add(sb.toString());
            return;
        }
        for(int i = 0;i<3;i++){
            sb.append(letters[i]);
            backtrack(res,letters,sb,len,l);
            sb.deleteCharAt(sb.length()-1);
        }
        
    }
    public static String getLthLargestString(int n, int l){
        String[] letters = {"a","b","c"};
        List<String> res = new ArrayList<>();
        backtrack(res,letters,new StringBuilder(),n,l);
        // System.out.println(res);
        return res.get(l-1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        System.out.println(getLthLargestString(n,l));
        sc.close();
    }
}