// Murali playing a mobile game, Blast the letters.

// In the game he is given a word W and value R.
// Murali has to perform the blasting operation as follows:
// 	- He has to blast the mimeograph M of length R in W, 
// 	  a mimeograph is a string such that each letter in it should be same.
// 	- After blasting the mimeograph, the rest of the string on its
// 	  left side and right side, concatenated together.

// Murali has to perform the blasting operation repeatedly, 
// until no more blasting is possible. Your task is to return 
// the final string after all the blast operations have been done.

// Input Format:
// -------------
// Line-1: A string and an integer, W and R.

// Output Format:
// --------------
// Print a string, the final string after all the blast operations have been done.


// Sample Input-1:
// --------------- 
// ababbaaab 3

// Sample Output-1:
// ----------------
// aba


// Sample Input-2:
// --------------- 
// caaabbbaacdddd 2

// Sample Output-2:
// ----------------
// cabc

import java.util.*;

public class BlastString{
    public static String blastLetters(String s, int k){
        int n = s.length();
        int count = 1;
        int start = -1;
        for(int i = 1;i<n;i++){
            if(s.charAt(i) == s.charAt(i-1)){
                count++;
                if(count >= k){
                    start = i-count+1;
                    break;
                }
            }
            else{
                count = 1;
            }
        }
        if(start == -1){
            return s;
        }
        s = s.substring(0,start) + s.substring(start + count);
        
        return blastLetters(s,k);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        System.out.println(blastLetters(s,k));
        sc.close();
    }
}