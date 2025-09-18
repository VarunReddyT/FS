// Mr Ajay Sharma is working woth words.
// He found that few words in the langugage have same meaning.
// Such words are given as a list of pairs as mappedpairs[],
// where mappedpairs[i] = [word1, word2] indicates that word1 and word2 are 
// the words with same meaning.

// He is given phrase, and he wants to apply all the mappedpairs[] on the phrase.

// Your task is to help Mr.Ajay Sharma to find and return all possible 
// Mapped Phrases generated after applying all the mapped words,
// and print them in lexicographical order.


// Input Format:
// -------------
// Line-1: An integer N, number of mapped pairs.
// Next N lines: Two space separated words, mappedpair[].
// Last Line: A line of words, the phrase.

// Output Format:
// --------------
// Print the list of mapped phrases in sorted order.


// Sample Input-1:
// ---------------
// 3
// hi hello
// sweet sugar
// candy chocolate
// hi sam! ram has a sugar candy

// Sample Output-1:
// ----------------
// [hello sam! he has sugar candy, hello sam! he has sugar chocolate, 
// hello sam! he has sweet candy, hello sam! he has sweet chocolate, 
// hi sam! he has sugar candy, hi sam! he has sugar chocolate, 
// hi sam! he has sweet candy, hi sam! he has sweet chocolate]



// Sample Input-2:
// ---------------
// 2
// hi hey
// hey hello
// hi how are you

// Sample Output-2:
// ----------------
// [hello how are you, hey how are you, hi how are you]


import java.util.*;

public class MapPhrasesInSentence{
    public static void dfs(Map<String, Set<String>> map, Set<String> temp, Set<String> visited, String word){
        visited.add(word);
        temp.add(word);
        if(map.containsKey(word)){
            for(String neighbour : map.get(word)){
                if(!visited.contains(neighbour)){
                    dfs(map,temp,visited,neighbour);
                }
            }
        }
    }
    public static void backtrack(Map<String, List<String>> map, String[] s, ArrayList<String> res, int index, ArrayList<String> temp){
        if(index == s.length){
            res.add(String.join(" ", temp));
            return;
        }
        String currWord = s[index];
        
        if(map.containsKey(currWord)){
            for(String neighbours : map.get(currWord)){
                temp.add(neighbours);
                backtrack(map,s,res,index+1,temp);
                temp.remove(temp.size()-1);
            }
        }
        else{
            temp.add(currWord);
            backtrack(map,s,res,index+1,temp);
            temp.remove(temp.size()-1);
        }
    }
    public static ArrayList<String> getAllSentences(String[][] arr, String[] s, int n){
        ArrayList<String> res = new ArrayList<>();
        Map<String, Set<String>> map = new HashMap<>();
        for(String[] i : arr){
            map.putIfAbsent(i[0], new HashSet<>());
            map.putIfAbsent(i[1], new HashSet<>());
            map.get(i[0]).add(i[1]);
            map.get(i[1]).add(i[0]);
        }
        
        Map<String, List<String>> groups = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        for(String word : map.keySet()){
            if(!visited.contains(word)){
                Set<String> temp = new HashSet<>();
                dfs(map,temp,visited,word);
                List<String> words = new ArrayList<>(temp);
                Collections.sort(words);
                for(String w : temp){
                    groups.put(w,words);
                }
            }
        }
        backtrack(groups,s,res,0,new ArrayList<>());
        
        Collections.sort(res);
        
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[][] arr = new String[n][2];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextLine().split(" ");
        }
        String[] s = sc.nextLine().split(" ");
        System.out.println(getAllSentences(arr,s,n));
        sc.close();
    }
}