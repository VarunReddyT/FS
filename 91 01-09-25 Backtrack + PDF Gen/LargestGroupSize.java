// Mr Saurabh is given a list of words.
// Your task is to help Mr Saurabh to find the size of largest group

// A group is formed using the following rules:
// - Pick one smallest word (in length) and form a group with this word
//   (if it is not part of any other group yet)
// - Add a letter at any place in the word without changing the relative order 
//   of the letters in it, and if it forms a word which is existing in the list[],
//   then add the word to the group.
// - Repeat the above two steps, till all the words in the list are part of groups.

// NOTE:You move form more than one group.

// Input Format:
// -------------
// Space separated words, wordsList[].

// Output Format:
// --------------
// Print an integer result


// Sample Input-1:
// ---------------
// many money n an mony any one mone on

// Sample Output-1:
// ----------------
// 5

// Explanation:
// ------------
// the words group is : [n, on, one, mone, money]


// Sample Input-2:
// ---------------
// a ab abb babb abab baba bab abbaa

// Sample Output-2:
// ----------------
// 4

import java.util.*;

public class LargestGroupSize{
        
    public static boolean isValid(String s, String neighbour){
        if(Math.abs(s.length() - neighbour.length()) != 1){
            return false;
        }
        int i = 0, j = 0, mismatch = 0;
        while(i<s.length() && j<neighbour.length()){
            if(s.charAt(i) == neighbour.charAt(j)){
                i++;
            }
            else{
                mismatch++;
                if(mismatch>1){
                    return false;
                }
            }
            j++;
        }
        return i == s.length();
    }
    
    public static int backtrack(Map<Integer, Set<String>> map, String s, Map<String, Integer> dp){
        if(dp.containsKey(s)){
            return dp.get(s);
        }
        int size = 1;
        int i = s.length();
        if(map.containsKey(i+1)){
            for(String neighbour : map.get(i+1)){
                if(isValid(s,neighbour)){
                    size = Math.max(size,1+backtrack(map,neighbour,dp));
                }
            }
        }
        dp.put(s,size);
        return size;
    }
    public static int getMaxWord(String[] input){
        int res = Integer.MIN_VALUE;
        Map<Integer, Set<String>> map = new HashMap<>();
        for(String s : input){
            map.putIfAbsent(s.length(), new HashSet<>());
            map.get(s.length()).add(s);
        }
        // if(!map.containsKey(1)){
        //     return 0;
        // }
        Map<String,Integer> dp = new HashMap<>();
        for(String s : input){
            res = Math.max(res,backtrack(map,s,dp));
        }
        // System.out.println(map);
        
        return res == Integer.MIN_VALUE ? 0 : res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        // Arrays.sort(input, (a,b)->(a.length()==b.length() ? a.compareTo(b) : a.length() - b.length()));
        System.out.println(getMaxWord(input));
        // System.out.println(Arrays.toString(input));

        sc.close();
    }
}