// 2. Longest word with all prefixes:
// Given an array of strings words, find the longest string in words such that every prefix of it is 
// also in words.
//  For example, let words = ["a", "app", "ap"]. 
// The string "app" has prefixes "ap" and "a", all of which are in words.
// Return the string described above. If there is more than one string with the same length, return 
// the lexicographically smallest one, and if no string exists, return "".
// Example 1:
// Input: words = [“k”,”ki”,”kir”,”kira”, “kiran”]
// Output: “kiran”
// Explanation: “kiran” has prefixes “kira”, “kir”, “ki”, and “k”, and all of them appear in words.
// Example 2:
// Input: words = [“a”, “banana”, “app”, “appl”, “ap”, “apply”, “apple”]
// Output: “apple”
// Explanation: Both “apple” and “apply” have all their prefixes in words. However, “apple” is 
// lexicographically smaller, so we return that.
// Example 3:
// Input: words = [“abc”, “bc”, “ab”, “qwe”]
// Output: “ ”

import java.util.*;

class Node{
    Node[] links;
    boolean flag;

    public Node(){
        links = new Node[26];
        flag = false;
    }

    public boolean containsKey(char ch){
        return links[ch-'a'] != null;
    }
    public Node get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch, Node node){
        links[ch-'a'] = node;
    }
    public void setEnd(){
        flag = true;
    }
    public boolean isEnd(){
        return flag;
    }
}

class Trie{
    private Node root;
    public Trie(){
        root = new Node();
    }
    public void insert(String word){
        Node node = root;
        for(char ch : word.toCharArray()){
            if(!node.containsKey(ch)){
                node.put(ch,node);
            }
            node = node.get(ch);
        }
        node.setEnd();
    }
    public boolean checkIfAllPrefixes(String word){
        Node node = root;
        boolean flag = true;
        for(char ch : word.toCharArray()){
            if(node.containsKey(ch)){
                node = node.get(ch);
                flag = flag && node.isEnd();
            }
            else{
                return false;
            }
        }
        return flag;
    }
}

public class LongestWordWithAllPrefixes {

    public static String completeString(int n, String[] dict){
        Trie obj = new Trie();
        for(String word : dict){
            obj.insert(word);
        }
        String longest = "";
        for(String word : dict){
            if(obj.checkIfAllPrefixes(word)){
                if(word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)){
                    longest = word;
                }
            }
        }
        if(longest.equals("")){
            return "None";
        }
        return longest;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dict[] = sc.nextLine().split(" ");
        System.out.println(completeString(dict.length, dict));
        sc.close();
    }
}
