import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int frequency;
    String word;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        frequency = 0;
        word = null;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int freq) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
        node.frequency = freq;
        node.word = word;
    }
    public void collectWords(TrieNode node, PriorityQueue<StringFrequency> pq, int k){
        if(node == null){
            return;
        }
        if(node.isEndOfWord){
            pq.offer(new StringFrequency(node.word, node.frequency));
            if(pq.size()>k){
                pq.poll();
            }
        }
        for(TrieNode child : node.children){
            if(child != null){
                collectWords(child, pq, k);
            }
        }
    }
}

class StringFrequency{
    String word;
    int freq;
    StringFrequency(String word, int freq){
        this.word = word;
        this.freq = freq;
    }
    class WordComparator implements Comparator<StringFrequency>{
        public int compare(StringFrequency a, StringFrequency b){
            if(a.freq == b.freq){
                return b.word.compareTo(a.word);
            }
            return Integer.compare(a.freq, b.freq);
        }
    }
}

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k){
        Map<String,Integer> freqMap = new HashMap<>();
        for(String word : words){
            freqMap.put(word,freqMap.getOrDefault(word, 0)+1);
        }
        Trie trie = new Trie();
        for(Map.Entry<String,Integer> entry : freqMap.entrySet()){
            trie.insert(entry.getKey(), entry.getValue());
        }
        PriorityQueue<StringFrequency> pq = new PriorityQueue<>();
        trie.collectWords(trie.root, pq, k);

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll().word);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dict[] = sc.nextLine().split(",");
        int k = sc.nextInt();
        System.out.println(new TopKFrequentWords().topKFrequent(dict, k));
        sc.close();
    }
}
