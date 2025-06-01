import java.util.Scanner;

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
