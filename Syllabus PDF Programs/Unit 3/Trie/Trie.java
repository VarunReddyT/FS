import java.util.*;

public class Trie {
    static class Node{
        Node[] links = new Node[26];
        boolean flag = false;

        boolean containsKey(char ch){
            return links[ch-'a'] != null;
        }

        void put(char ch, Node node){
            links[ch - 'a'] = node;
        }

        Node get(char ch){
            return links[ch-'a'];
        }
        void setEnd(){
            flag = true;
        }
        void unsetEnd(){
            flag = false;
        }

        boolean isEnd(){
            return flag;
        }

        boolean isEmpty(){
            for(int i = 0;i<26;i++){
                if(links[i] != null){
                    return false;
                }
            }
            return true;
        }
    }
    private final Node root;
    public Trie(){
        root = new Node();
    }
    public void insert(String word){
        Node node = root;
        for(char ch : word.toCharArray()){
            if(!node.containsKey(ch)){
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }
    public boolean search(String word){
        Node node = root;
        for(char ch : word.toCharArray()){
            if(!node.containsKey(ch)){
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }
    public boolean startsWith(String prefix){
        Node node = root;
        for(char ch : prefix.toCharArray()){
            if(!node.containsKey(ch)){
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }
    public boolean delete(String word){
        if(!search(word)){
            return false;
        }
        deleteHelper(root,word,0);
        return true;
    }
    public boolean deleteHelper(Node current, String word, int index){
        if(index == word.length()){
            current.unsetEnd();
            return current.isEmpty();
        }
        char ch = word.charAt(index);
        Node next = current.get(ch);
        if(next == null){
            return false;
        }
        boolean shouldDeleteNextNode = deleteHelper(current, word, index+1);
        if(shouldDeleteNextNode){
            current.links[ch-'a'] = null;
            return !current.isEnd() && current.isEmpty();
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
