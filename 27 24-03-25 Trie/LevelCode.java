// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when all
// of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. 
// If there is more than one valid code of the same length, choose the one that 
// comes first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every 
// prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
// the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""


import java.util.*;

class TrieNode{
    TrieNode [] children;
    boolean end ;
    TrieNode(){
        children = new TrieNode[26];
        end = false;
    }
    
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    boolean insert(String str){
        boolean flag = (str.length()==1)? false:true;
        TrieNode node = root;
        char temp [] = str.toCharArray();
        for(int i = 0; i < temp.length ; i++){
            int ind = temp[i] - 'a';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
            if((i < (temp.length -1)) && (node.end==false)){
                flag = false;
            }
        }
        node.end = true;
        return flag;
    }
    boolean search (String str){
        TrieNode node = root;
        for(char c : str.toCharArray()){
            if(node.children[c-'a']==null){
                return false;
            }
            node = node.children[c-'a'];
        }
        return node.end;
    }
}
public class LevelCode{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String inp [] = sc.nextLine().split(" ");
        System.out.print(find(inp));
        sc.close();
    }
    static String find(String inp []){
        Arrays.sort(inp,(a,b)->a.length()-b.length());
        String res = "";
        Trie t = new Trie();
        for(String i :inp){
            if(t.insert(i)){
                if(res.length()<i.length()){
                    res = i;
                }
                if((res.length()==i.length()) && (i.compareTo(res)<0) ){
                    res = i;
                }
            }
        }
        return res;
    }
}