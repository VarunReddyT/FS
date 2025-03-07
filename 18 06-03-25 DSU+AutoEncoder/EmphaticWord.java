// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2


import java.util.*;

public class EmphaticWord{
    public static boolean check(String curr, HashMap<Character,Integer> map){
        HashMap<Character,Integer> temp = new HashMap<>();
        for(char c : curr.toCharArray()){
            if(!map.containsKey(c)){
                return false;
            }
            temp.put(c,temp.getOrDefault(c,0)+1);
        }
        for(char c: curr.toCharArray()){
            if(map.get(c) < temp.get(c)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int count = 0;
        for(String curr : input){
            if(check(curr,map)){
                count++;
            }
        }
        System.out.println(count);
        sc.close();
    }
}