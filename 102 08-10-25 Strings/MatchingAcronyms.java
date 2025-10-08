// Ananth interested in creating the acronyms for any word. The definition
// of acronym is another word with a concatenation of its first letter,
// the number of letters between the first and last letter, and its last letter. 
 
// If a word has only two characters, then it is an acronym of itself.

// Examples:
//     - Acronym of 'fog' is f1g'.
//     - Acronym of 'another' is 'a5r'.
//     - Acronym of 'ab' is 'ab'.

// You are given a list of vocabulary, and another list of words.
// Your task is to check,each word with the vocabulary and
// return "true" if atleast one of the following rules satisfied:
// 1. acronym of the word should not match with any of the acronyms of vocabulary
// 2. if acronym of the word matches with any of the acronyms of vocabulary
// 'the word' and matching words in vocabulary should be same.

// Otherwise, return 'false'.

// Input Format:
// -------------
// Line-1: Space separated strings, vocabulary[] 
// Line-2: Space separated strings, words[] 

// Output Format:
// --------------
// Print N boolean values, where N = words.length 


// Sample Input-1:
// ---------------
// cool bell cool coir move more mike
// cool char move 

// Sample Output-1:
// ----------------
// true false false

import java.util.*;

public class MatchingAcronyms{
    public static String getAcronym(String word){
        if(word.length() <= 2){
            return word;
        }
        return word.charAt(0) + Integer.toString(word.length()-2) + word.charAt(word.length()-1);
        
    }
    public static void checkAcronyms(String[] voc, String[] words){
        Set<String> vocabulary = new HashSet<>();
        Set<String> acronyms = new HashSet<>();
        Set<String> duplicateAcronyms = new HashSet<>();
        for(String i : voc){
            vocabulary.add(i);
        }
        for(String i : vocabulary){
            String acr = getAcronym(i);
            if(acronyms.contains(acr)){
                duplicateAcronyms.add(acr);
            }
            else{
                acronyms.add(acr);
            }
        }
        // System.out.println("Vocabulary : " + vocabulary);
        // System.out.println("Acronyms : " + acronyms);
        for(String i : words){
            String acr = getAcronym(i);
            if(!acronyms.contains(acr)){
                System.out.print("true ");
            }
            else if(acronyms.contains(acr) && !duplicateAcronyms.contains(acr) && vocabulary.contains(i)){
                System.out.print("true ");
            }
            else{
                System.out.print("false ");
            }
        }
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] voc = sc.nextLine().split(" ");
        String[] words = sc.nextLine().split(" ");
        checkAcronyms(voc,words);
        sc.close();
    }
}