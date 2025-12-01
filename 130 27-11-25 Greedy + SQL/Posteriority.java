/*Vihaan is given a list of words[]. 
He is asked to create a method which returns the numbers of characters in a word 
formed from long lasting frequent posteriority.

Posteriority is the word formed from the original word with few characters removed
without modifying the corresponding order of the left over characters.

An uncommon posteriority between the list of words is a word that is a posteriority
of one word but not the others.

Your task is to find the longest uncommon posteriority of the list of words.
Return 0 if no uncommon posteriority.


Input Format:
-------------
Space separated strings words[]

Output Format:
--------------
Print an integer, the length of longest uncommon prosperity.


Sample Input-1:
---------------
mom rar ama

Sample Output-1:
----------------
3


Sample Input-2:
---------------
ppp ppp pp

Sample Output-2:
----------------
-1
 */

import java.util.*;

public class Posteriority{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String words [] = cin.nextLine().split(" ");
        System.out.println(find(words));
        cin.close();
    }
    static int find(String words[]){
        Arrays.sort(words,(a,b)->b.length()-a.length());
        // check first word
        int n = words.length;
        boolean exists = false;
        for(int i = 1; i< n; i++){
            if(words[i].length() < words[0].length()){
                break;
            }
            else if(words[0].equals(words[i])){
                exists = true;
                break;
            }
        }
        if(!exists){
            return words[0].length();
        }
        
        // check other words from 1 to n. 
        outer : for(int i = 1; i < n; i++){
            for(int j = 0; j< i;j++){
                if(checkSubSeq(words[i],words[j])){
                    continue outer;
                }
            }
            return words[i].length();
        }
        return -1;
    }
    static boolean checkSubSeq(String s, String t){
        int ind = 0;
        for(int i = 0; i < t.length() && ind < s.length(); i++){
            if(s.charAt(ind)==t.charAt(i)){
                ind++;
            }
        }
        return ind==s.length();
    }
}