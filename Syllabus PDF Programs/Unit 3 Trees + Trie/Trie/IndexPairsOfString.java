// 1. Index Pairs of a String:
// Given a string text and an array of strings words, return an array of all index pairs [i, j] 
// so that the substring text[i...j] is in words. Return the pairs [i, j] in sorted order (i.e., sort them by 
// their first coordinate, and in case of ties sort them by their second coordinate).
// Example 1:
// Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
// Output: [[3,7],[9,13],[10,17]]
// Example 2:
// Input: text = "ababa", words = ["aba","ab"]
// Output: [[0,1],[0,2],[2,3],[2,4]]
// Explanation: Notice that matches can overlap, see "aba" is found in [0,2] and [2,4]

import java.util.*;

public class IndexPairsOfString {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public List<int[]> indexPairs(String text, String[] words) {
        for (String i : words) {
            insert(i);
        }
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            TrieNode node = root;
            int j = i;
            while (j < text.length()) {
                int index = text.charAt(j) - 'a';
                if (node.children[index] == null) {
                    break;
                }
                node = node.children[index];
                if (node.isEndOfWord) {
                    result.add(new int[] { i, j });
                }
                j++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String words[] = sc.nextLine().split(" ");
        IndexPairsOfString solution = new IndexPairsOfString();
        printResult(solution.indexPairs(text, words));
        sc.close();
    }

    private static void printResult(List<int[]> pairs) {
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
