// In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
// You have two encoded keys, key1 and key2, both of equal length. Each character 
// in key1 is paired with the corresponding character in key2. 

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.  
// • Mutual Mapping: If a character from key1 maps to one in key2, then that 
//   character in key2 maps back to the one in key1.  
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
//   are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every character 
// with the smallest equivalent character from its equivalence group. 
// The result should be the relatively smallest possible decoded message.


// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of cipherText.

// Example 1: 
// input=
// attitude progress apriori
// output=
// aaogoog


// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
// [d, e, s]. By substituting each character in cipherText with the smallest from 
// its group, you decode the message to "aaogoog".


// Constraints:  
// • 1 <= key1.length, key2.length, cipherText.length <= 1000  
// • key1.length == key2.length  
// • key1, key2, and cipherText consist solely of lowercase English letters.

import java.util.*;

public class DecodeCipher{
    public static int[] initParent(int[] rank){
        int[] arr = new int[26];
        for(int i = 0;i<26;i++){
            arr[i] = i;
            rank[i] = i;
        }
        return arr;
    }
    public static void union(int[] parent, int[] rank, int x, int y){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX == rootY){
            return;
        }
        if(rank[rootX] > rank[rootY]){
            parent[rootX] = rootY;
        }
        else if(rank[rootY] > rank[rootX]){
            parent[rootY] = rootX;
        }
        else{
            parent[rootY] = rootX;
            rank[rootX]--;
        }
    }
    public static int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    public static String solve(String key1, String key2, String cipher){
        int[] rank = new int[26];
        int[] arr = initParent(rank);
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<key1.length();i++){
            union(arr,rank,key1.charAt(i)-'a',key2.charAt(i)-'a');
        }
        for(int i = 0;i<cipher.length();i++){
            int x = find(arr,cipher.charAt(i)-'a');
            sb.append((char)(x+97));
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String key1 = input[0];
        String key2 = input[1];
        String cipher = input[2];
        System.out.println(solve(key1,key2,cipher));
        sc.close();
    }
}