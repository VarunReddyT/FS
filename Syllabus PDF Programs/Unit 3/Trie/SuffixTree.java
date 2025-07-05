import java.util.*;

class SuffixTrie{
    static class SuffixTrieNode{
        SuffixTrieNode[] children = new SuffixTrieNode[26];
        boolean isEndOfWord;
        SuffixTrieNode(){
            isEndOfWord = false;
            for(int i = 0;i<26;i++){
                children[i] = null;
            }
        }
        static SuffixTrieNode root;

        static void insert(String word){
            SuffixTrieNode currentNode = root;
            for(int level = 0;level<word.length();level++){
                int index = word.charAt(level) - 'a';
                if(currentNode.children[index] == null){
                    currentNode.children[index] = new SuffixTrieNode();
                }
                currentNode = currentNode.children[index];
            }
            currentNode.isEndOfWord = true;
        }
        static void print(SuffixTrieNode root, char[] str, int level){
            if(root.isEndOfWord){
                for(int k = level;k<str.length;k++){
                    str[k] = 0;
                }
                System.out.println(str);
            }
            for(int i = 0;i<26;i++){
                if(root.children[i] != null){
                    str[level] = (char)(i+'a');
                    print(root.children[i], str, level+1);
                }
            }
        }
    }
}

public class SuffixTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any string to construct suffix tree");
        String word = sc.nextLine();
        SuffixTrie.SuffixTrieNode.root = new SuffixTrie.SuffixTrieNode();
        for (int i = 0; i < word.length(); i++) {
            SuffixTrie.SuffixTrieNode.insert(word.substring(i));
        }
        char[] str = new char[word.length()];
        SuffixTrie.SuffixTrieNode.print(SuffixTrie.SuffixTrieNode.root, str, 0);
        sc.close();
    }
}
