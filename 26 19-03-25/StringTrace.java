// Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
// ancient runes, where each cell holds a single character. Legend has a 
// powerful incantation—represented as a string—is hidden within these runes. 
// To unlock the treasure, you must verify if the incantation exists on the map.

// The incantation is formed by linking runes that are directly next to each other 
// either horizontally or vertically. Each rune on the map can only be used once in
// the incantation.

// Your Task:  
// Given an m x n grid representing the treasure map and a string representing the 
// incantation, return true if the incantation can be traced on the map; 
// otherwise, return false.


// Example 1:
// ----------
// Input:  
// 3 4
// ABCD
// SFCS
// ADEE
// ABCCED

// Output:
// ABCCED can be traced

// Explanation (check hint)
// Treasure Map Grid:  
// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCCED" exists in map


// Example 2:
// ----------
// Input:
// 3 4
// ABCE
// SFCS
// ADEE
// ABCB

// Output: 
// ABCB cannot be traced

// Explanation:
// Treasure Map Grid:  

// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCB" does not exist in map


// Constraints:

// - m == the number of rows in the grid  
// - n == the number of columns in the grid  
// - 1 <= m, n <= 6  
// - 1 <= incantation length <= 15  
// - The grid and incantation consist only of uppercase and lowercase English letters.

import java.util.*;

public class StringTrace{
    public static boolean dfs(char[][] arr, int m, int n, int i, int j,String word, int curr){
        if(curr == word.length()){
            return true;
        }
        if(i < 0 || i>= m || j<0 || j>=n || arr[i][j] == '0' || arr[i][j] != word.charAt(curr)){
            return false;
        }
        char temp = arr[i][j];
        arr[i][j] = '0';
        boolean flag = dfs(arr,m,n,i+1,j,word,curr+1) || dfs(arr,m,n,i-1,j,word,curr+1) || dfs(arr,m,n,i,j+1,word,curr+1) || dfs(arr,m,n,i,j-1,word,curr+1);
        if(flag){
            return true;
        }
        arr[i][j] = temp;
        return false;
    }
    public static boolean isPresent(char[][] arr, int m, int n, String word){
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(arr[i][j] == word.charAt(0)){
                    if(dfs(arr,m,n,i,j,word,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] arr = new char[m][n];
        for(int i = 0;i<m;i++){
            String temp = sc.next();
            arr[i] = temp.toCharArray();
        }
        String word = sc.next();
        if(isPresent(arr,m,n,word)){
            System.out.println(word + " can be traced");
        }
        else{
            System.out.println(word + " cannot be traced");
        }
        sc.close();
    }
}