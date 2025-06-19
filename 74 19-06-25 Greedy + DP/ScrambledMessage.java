// A secret agent encodes a message by recursively scrambling it using a random encryption 
// protocol.  The encryption process follows these rules:
//  - If the message is a single character, leave it unchanged.
//  - If the message has more than one character:
// 		- Split the message into two non-empty parts at any random position.
// 		- With a 50% chance, swap the two parts; otherwise, keep them in the same order.
// 		- Repeat this scrambling recursively on each part.
         
// This encryption method produces a scrambled version of the original message.

// You are now given two messages:
// original: the message before scrambling.
// scrambled: a possibly scrambled version of the original message.

// Write a program to determine whether the scrambled message could have been produced 
// by scrambling the original message using the above protocol.

// Sample Input:
// -------------
// Two strings, original and scrambled, each of equal length.

// Sample Output:
// ---------------
// Return true if the scrambled string could be a scrambled version of the original using 
// the given encryption protocol. Otherwise, return false.


// Sample Input:
// -------------
// secure cesure

// Sample Output:
// ---------------
// true

// Explanation: 
// ------------
// One possible scrambling path leads from "secure" to "cesure".

// Sample Input:
// -------------
// planet npealt

// Sample Output:
// ---------------
// false

// Explanation: 
// ------------
// No sequence of valid splits and swaps can lead to "petlan" from "npealt".


import java.util.*;

public class ScrambledMessage{
    public static boolean isScrambled(String s1, String s2) {
        if (s1.equals(s2)){
            return true;
        }
        if (s1.length() != s2.length()){
            return false;
        }
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        if (!Arrays.equals(a1, a2)){
            return false;
        }

        int n = s1.length();
        for (int i = 1; i < n; i++) {
            if (isScrambled(s1.substring(0, i), s2.substring(0, i)) && isScrambled(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScrambled(s1.substring(0, i), s2.substring(n - i)) && isScrambled(s1.substring(i), s2.substring(0, n - i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(isScrambled(input[0], input[1]));
        sc.close();
    }
}
