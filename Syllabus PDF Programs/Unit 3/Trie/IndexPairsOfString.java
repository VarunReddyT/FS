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
