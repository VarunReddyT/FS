import java.util.*;

public class TrieSimple {
    static class Node {
        Node[] children = new Node[26];
        boolean flag = false;

        boolean isEmpty() {
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    private final Node root;

    public TrieSimple() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }
            node = node.children[ch - 'a'];
        }
        node.flag = true;
    }

    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return node.flag;
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return true;
    }

    public boolean delete(String word) {
        if (!search(word)) {
            return false;
        }
        deleteHelper(root, word, 0);
        return true;
    }

    public boolean deleteHelper(Node current, String word, int index) {
        if (index == word.length()) {
            current.flag = false;
            return current.isEmpty();
        }
        char ch = word.charAt(index);
        Node next = current.children[ch - 'a'];
        if (next == null) {
            return false;
        }
        boolean shouldDeleteNextNode = deleteHelper(next, word, index + 1);
        if (shouldDeleteNextNode) {
            current.children[ch - 'a'] = null;
            return !current.flag && current.isEmpty();
        }
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter strings:");
        String[] str = sc.nextLine().split(" ");
        for (String s : str) {
            trie.insert(s);
        }
        System.out.println("Strings are inserted in Trie.");

        // Search
        System.out.println("Enter a word to search:");
        String searchWord = sc.next();
        System.out.println(trie.search(searchWord) ? "True" : "False");

        // Prefix check
        System.out.println("Enter a prefix to check:");
        String prefix = sc.next();
        System.out.println(trie.startsWith(prefix) ? "True" : "False");

        // Delete
        System.out.println("Enter a word to delete:");
        String deleteWord = sc.next();
        boolean deleted = trie.delete(deleteWord);
        System.out.println(deleted ? "Deleted successfully" : "Word not found");

        sc.close();
    }
}
