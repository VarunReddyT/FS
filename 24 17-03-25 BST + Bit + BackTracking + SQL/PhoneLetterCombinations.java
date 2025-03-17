// Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

// Below is the mapping of digits to letters (as found on a traditional telephone keypad):

// | Digit | Letters       |
// |-------|---------------|
// | 2     | a, b, c       |
// | 3     | d, e, f       |
// | 4     | g, h, i       |
// | 5     | j, k, l       |
// | 6     | m, n, o       |
// | 7     | p, q, r, s    |
// | 8     | t, u, v       |
// | 9     | w, x, y, z    |

// Note: The digit 1 does not correspond to any letters.

// Example 1:
// Input: 23  
// Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

// Example 2:
// Input: 2 
// Output: [a, b, c]


// Constraints:

// - 0 <= digits.length <= 4  
// - Each digit in the input is between '2' and '9'.


import java.util.*;

public class PhoneLetterCombinations{
    public static void backtrack(StringBuilder sb, String num, ArrayList<String> res, HashMap<Integer,ArrayList<Character>> map, int curr){
        if(curr == num.length()){
            res.add(sb.toString());
            return ;
        }
        ArrayList<Character> letters = map.get(num.charAt(curr)-'0');
        for(int i = 0;i<letters.size();i++){
            sb.append(letters.get(i));
            backtrack(sb,num,res,map,curr+1);
            sb.deleteCharAt(sb.length()-1);
        }
        
    }
    public static ArrayList<String> getCombinations(int n){
        HashMap<Integer,ArrayList<Character>> map = new HashMap<>();
        map.put(2,new ArrayList<Character>(Arrays.asList('a','b','c')));
        map.put(3,new ArrayList<Character>(Arrays.asList('d','e','f')));
        map.put(4,new ArrayList<Character>(Arrays.asList('g','h','i')));
        map.put(5,new ArrayList<Character>(Arrays.asList('j','k','l')));
        map.put(6,new ArrayList<Character>(Arrays.asList('m','n','o')));
        map.put(7,new ArrayList<Character>(Arrays.asList('p','q','r','s')));
        map.put(8,new ArrayList<Character>(Arrays.asList('t','u','v')));
        map.put(9,new ArrayList<Character>(Arrays.asList('w','x','y','z')));
        
        ArrayList<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String num = Integer.toString(n);

        backtrack(sb,num,res,map,0);
        
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getCombinations(n));
        sc.close();
    }
}