// Mr Suresh is working with the plain text P, a list of words w[], 
// He is converting P into C [the cipher text], C is valid cipher of P, 
// if the following rules are followed:
// 	- The cipher-text C is a string ends with '$' character.
// 	- Every word, w[i] in w[], should be a substring of C, and 
// 	the substring should have $ at the end of it.

// Your task is to help Mr Suresh to find the shortest Cipher C,  
// and return its length.

// Input Format:
// -------------
// Single line of space separated words, w[].

// Output Format:
// --------------
// Print an integer result, the length of the shortest cipher.


// Sample Input-1:
// ---------------
// kmit it ngit

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// A valid cipher C is "kmit$ngit$".
// w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
// w[1] = "it", the substring of C, and the '$' is the end character after "it"
// w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


// Sample Input-2:
// ---------------
// ace

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// A valid cipher C is "ace$".
// w[0] = "ace", the substring of C, and the '$' is the end character after "ace"


import java.util.*;

class TrieNode{
    TrieNode [] children;
    boolean isEnd;
    TrieNode(){
        children = new TrieNode[26];
        isEnd = false;
    }
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    boolean insert(String s){
        TrieNode node = root;
        boolean isNew = false;
        char temp [] = s.toCharArray();
        for(int i = temp.length-1 ; i>=0 ; i--){
            int index = temp[i] - 'a';
            if(node.children[index]==null){
                node.children[index] = new TrieNode();
                isNew = true;
            }
            node = node.children[index];
        }
        node.isEnd = true;
        return isNew;
    }

}
public class ShortestCipher{
    public static int solve(String[] s){
        Trie trie = new Trie();
        int res = 0;
        Arrays.sort(s,(a,b)->b.length()-a.length());
        for(String word : s){
            if(trie.insert(word)){
                res += word.length()+1;
            }
        }
        return res;
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        System.out.print(solve(s));
        sc.close();
    }
}