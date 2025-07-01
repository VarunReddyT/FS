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

public class LongestWordWithAllPrefixes {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
    static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        public boolean allPrefixesExist(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children[c - 'a'];
                if (node == null || !node.isEnd) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String completeString(int n, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        String longest = "";
        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }

        return longest.equals("") ? "None" : longest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(" ");
        System.out.println(completeString(dict.length, dict));
        sc.close();
    }
}
