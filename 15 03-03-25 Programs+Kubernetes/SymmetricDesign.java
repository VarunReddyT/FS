// Imagine you are an artisan tasked with assembling a decorative mosaic from a 
// collection of uniquely colored tiles. Each tile is marked with a character, 
// and your challenge is to rearrange these tiles to create a design that mirrors 
// itself perfectly from left to right. 
// Your goal is to determine whether the available tiles can be arranged to form 
// such a symmetric pattern. Print true if a symmetric design is possible,
// and false otherwise.


// Input format:
// A string representing the characters on the tiles.

// Output format:
// Print a boolean value

// Example 1:
// input: work
// output: false

// Example 2:
// input: ivicc
// output: true

// Constraints:
// 1 <= string.length <= 5000
// tile characters are all lowercase English letters.


import java.util.*;

public class SymmetricDesign{
    public static boolean isSymmetric(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        for(Character c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int oddCount = 0;
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue()%2 != 0){
                oddCount++;
            }
        }
        if(oddCount <= 1){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s  = sc.next();
        System.out.println(isSymmetric(s));
        sc.close();
    }
}