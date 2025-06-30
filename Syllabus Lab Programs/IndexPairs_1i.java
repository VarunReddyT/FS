// i) Write a JAVA Program to return all index pairs [i,j] given a text string and words (a 
// list), so that the substring text[i]…text[j] is in the list of words
// Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring 
// text[i]...text[j] is in the list of words.
// Note:
// • All strings contains only lowercase English letters.
// • It's guaranteed that all strings in words are different.
// • Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them 
// by their second coordinate).
// Example 1:
// Input: text = "thestoryofleetcodeandme", words = 
// ["story","fleet","leetcode"] Output: [[3,7],[9,13],[10,17]]
// Example 2:
// Input: text = "ababa", words = ["aba","ab"] 
// Output: [[0,1],[0,2],[2,3],[2,4]]
// Explanation:
// Notice that matches can overlap, see "aba" is found in [0,2] and [2,4]

import java.util.*;
public class IndexPairs_1i {
    public int[][] indexPairs(String text, String[] words){
        List<int[]> indexPairsList = new ArrayList<>();
        for(String word : words){
            int wordLength = word.length();
            int currIndex = 0;
            while(currIndex >= 0){
                currIndex = text.indexOf(word, currIndex);
                if(currIndex >= 0){
                    indexPairsList.add(new int[]{currIndex, currIndex + wordLength - 1});
                    currIndex++;
                }
            }
        }
        Collections.sort(indexPairsList, new Comparator<int[]>(){
            public int compare(int[] array1, int[] array2){
                return ((array1[0] != array2[0]) ? Integer.compare(array1[0], array2[0]) : Integer.compare(array1[1], array2[1]));
            }
        });
        int length = indexPairsList.size();
        int[][] indexPairs = new int[length][2];

        for(int i = 0; i < length; i++){
            int[] indexPair = indexPairsList.get(i);
            indexPairs[i][0] = indexPair[0];
            indexPairs[i][1] = indexPair[1];
        }
        return indexPairs;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[] words = sc.nextLine().split(" ");
        int[][] result = new IndexPairs_1i().indexPairs(text, words);
        for(int[] res : result){
            System.out.print(Arrays.toString(res));
        }
        sc.close();
    }
}
