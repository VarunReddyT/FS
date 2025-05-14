// Mr NOkayya is AI developer, He is given a dictionary, he want to implement a 
// an Word-Corrector application that checks user-word and corrects it.

// For a given user-word, the Word-Corrector handles two types of validations:

// 1) If the user-word matches a word in the dictionary (case-insensitive), 
// then the user-word is returned with the matched word in the dictionary.
//     Ex-1: dict = ["kmit"], user-word = "KmIt": word-corrector = "kmit"
// 	Ex-2: dict = ["Kmit"], user-word = "kmit": word-corrector = "Kmit"
// 	Ex-3: dict = ["kmit"], user-word = "kmit": word-corrector = "kmit"
	
// 2) If after replacing the vowels of the user-word with any vowel individually,
// it matches a word in the dictionary (case-insensitive), 
// then the user-word is returned with the matched word in the dictionary.
// 	Ex-1: dict = ["KmIt"], user-word = "kmet": word-corrector = "KmIt"
// 	Ex-2: dict = ["KmIt"], user-word = "kmmit": word-corrector = "" (no match)
// 	Ex-3: dict = ["KmIt"], user-word = "kit": word-corrector = "" (no match)

// In addition to the above conditions, the word-corrector app works
// with the following precedence rules:
//  - When the user-word exactly matches a word in the dictionary (case-sensitive), 
//     you should return the same word back.
//  - When the user-word matches a word up to validation-1, 
//     you should return the first such match in the dictionary.
//  - When the user-word matches a word up to validation-2, 
//     you should return the first such match in the dictionary.
//  - If the user-word has no matches in the dictionary, 
//     you should return the empty string.

// You are given some user-words[], return a list of words result[], 
// where result[i] is the corrected words by the Word-Corrector app for 
// user-word = user-words[i].


// Input Format:
// -------------
// Line-1: comma separated strings, dictionary[].
// Line-2: comma separated strings, user-words[].

// Output Format:
// --------------
// List of corrected user-words[] by Word-Corrector app.


// Sample Input-1:
// ---------------
// LiTe,lite,bare,Bare
// lite,Lite,LiTe,Bare,BARE,Bear,bear,leti,leet,leto

// Sample Output-1:
// ----------------
// [lite, LiTe, LiTe, Bare, bare, , , LiTe, , LiTe]


// Sample Input-2:
// ---------------
// kmit,ngit,kmec
// KmOT,NHIT,KMIC

// Sample Output-2:
// ----------------
// [kmit, , kmec]


import java.util.*;

public class UserWordsCorrector{
    public static boolean isSame(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        Set<Character> set = Set.of('a','e','i','o','u','A','E','I','O','U');
        for(int i = 0;i<s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(set.contains(c1) && set.contains(c2)){
                continue;
            }
            if(set.contains(c1) ^ set.contains(c2)){
                return false;
            }
            if(!set.contains(c1) && !set.contains(c2)){
                if(Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static List<String> getWords(String[] dict, String[] userwords){
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        for(String i : dict){
            set.add(i);
        }
        for(String i : userwords){
            if(set.contains(i)){
                res.add(i);
                continue;
            }
            boolean check = false;
            for(String j : dict){
                if(i.equalsIgnoreCase(j)){
                    res.add(j);
                    check = true;
                    break;
                }
            }
            if(check){
                continue;
            }
            for(String j : dict){
                if(isSame(i,j)){
                    res.add(j);
                    check = true;
                    break;
                }
            }
            if(!check){
                res.add(" ");
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(",");
        String[] userwords = sc.nextLine().split(",");
        System.out.println(getWords(dict,userwords));
        sc.close();
    }
}