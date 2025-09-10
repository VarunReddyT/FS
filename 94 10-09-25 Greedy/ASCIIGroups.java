// Govind is playing with words, He find that few words have the same pattern
// like the following: abc <-> bcd <-> .. <-> mno <-> pqr <->..<-> xyz

// The pattern is formed by shifting each letter by either adding or subtracting 'i' 
// in alphabetic order either in forwarding direction or backward.

// e.g.: letters of 'abc' can be shift by 12-positions forward to match with 'mno'
// and letters of 'mno' can be shift by 12-positions backword to match with 'abc'.

// He is given a list of space-separated strings, words[].
// Your task is to help him to form the word-groups having the same pattern.

// NOTE: 
// ------
// 	- word-groups should be formed as per input order 
// 	and each group should be a sorted word-group.
// 	- shifting of letters can be circular in thier ASCII order like as follows:
// 		abc..yz
// 	e.g.: (abc, yza, zab) can form word-group like - [[abc, yza, zab]]

// Input Format:
// -------------
// A single line Space separated strings, words[]

// Output Format
// --------------
// Print the output, as described in the samples below.


// Sample Input-1:
// --------------
// abc bcd acef xyz az ba

// 3 - [abc, bcd, xyz]
// 4 - [acef]
// 2 - [az, ba]



// Sample Output-1:
// -----------------
// [[abc, bcd, xyz], [acef], [az, ba]]


// Sample Input-2:
// --------------
// yurp qmjh gdba jfca

// Sample Output-2:
// ----------------
// [[jfca, qmjh, yurp], [gdba]]


// Sample Input-3:
// ----------------
// abc xyz yza zbc yab acd

// Sample Output-3:
// ------------------
// [[abc, xyz, yza], [acd, yab, zbc]]

import java.util.*;

public class ASCIIGroups{
    public static ArrayList<ArrayList<String>> getGroups(String[] input){
        int n = input.length;
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        
        for(int i = 0;i<n;i++){
            if(visited[i]){
                continue;
            }
            ArrayList<String> temp = new ArrayList<>();
            temp.add(input[i]);
            visited[i] = true;
            for(int j = 0;j<n;j++){
                if(i == j || visited[j]){
                    continue;
                }
                String s1 = input[i];
                String s2 = input[j];
                if(s1.length() != s2.length()){
                    continue;
                }
                boolean flag = false;
                for(int k = 0;k<s1.length()-1;k++){
                    if((s1.charAt(k) - s1.charAt(k+1) + 26)%26 != (s2.charAt(k) - s2.charAt(k+1) + 26)%26){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    continue;
                }
                visited[j] = true;
                temp.add(s2);
                
            }
            Collections.sort(temp);
            res.add(temp);
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(getGroups(input));
        sc.close();
    }
}