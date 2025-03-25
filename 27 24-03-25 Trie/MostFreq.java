// Imagine you’re managing a busy cafe where every order is logged. You have a 
// record—a list of dish names ordered throughout the day—and you want to determine
// which dishes are the most popular. Given an list of strings representing the dish
// names and an integer P, your task is to return the P most frequently ordered dishes.

// The results must be sorted by the number of orders, from the most frequent to 
// the least. If two dishes have been ordered the same number of times, they should
// be listed in alphabetical order.

// Input Format:
// -------------
// Line-1: comma separated line of words, list of words.
// Line-2: An integer P, number of words to display. 

// Output Format:
// --------------
// Print P number of most common used words.

// Example 1:
// ----------
// Input=
// coffee,sandwich,coffee,tea,sandwich,muffin
// 2
// Output=
// [coffee, sandwich]

// Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
// Although both appear frequently, "coffee" is placed before "sandwich" because 
// it comes earlier alphabetically.

// Example 2:
// ----------
// Input=
// bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
// 3
// Output=
// [bagel, muffin, scone] 

// Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
// with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
// have the same frequency, they are ordered alphabetically.

// Constraints:

// - 1 ≤ orders.length ≤ 500  
// - 1 ≤ orders[i].length ≤ 10  
// - Each orders[i] consists of lowercase English letters.  
// - P is in the range [1, The number of unique dish names in orders].

import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    int count;

    TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
        count = 0;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
        node.count++;
    }

    void collectWords(TrieNode node, StringBuilder currentWord, List<Pair> words) {
        if (node == null) {
            return;
        }
        if (node.isEnd) {
            words.add(new Pair(currentWord.toString(), node.count));
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                currentWord.append((char) ('a' + i));
                collectWords(node.children[i], currentWord, words);
                currentWord.deleteCharAt(currentWord.length() - 1);
            }
        }
    }
}

class Pair {
    String word;
    int count;

    Pair(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

public class MostFreq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(",");
        int p = sc.nextInt();
        System.out.println(find(inp, p));
        sc.close();
    }

    static List<String> find(String[] inp, int p) {
        Trie trie = new Trie();
        for (String word : inp) {
            trie.insert(word);
        }

        List<Pair> wordsWithCounts = new ArrayList<>();
        trie.collectWords(trie.root, new StringBuilder(), wordsWithCounts);

        Collections.sort(wordsWithCounts, new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                if (a.count != b.count) {
                    return b.count - a.count;
                } else {
                    return a.word.compareTo(b.word);
                }
            }
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            result.add(wordsWithCounts.get(i).word);
        }

        return result;
    }
}