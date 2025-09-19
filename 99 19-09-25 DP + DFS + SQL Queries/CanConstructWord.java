// Given an 2D character array, Letters[][], of size r*c.
// You have to construct the word W, using the given array.

// Rules to construct the word are as follows:
// 	- All the letters of the word W, should be adjacent to each other 
// 	in the array Letters(either horizontal or vertical).
// 	- You can use each charcater in Letters[][] only once.

// If you are able to construct the word W, return 'true'
// Otherwise 'false'.

// Input Format:
// -------------
// Line-1 -> two integers R and C, array size.
// R lines -> C space separated characters.
// Last line -> a string, word W

// Output Format:
// --------------
// print the boolean result.


// Sample Input-1:
// ---------------
// 3 3
// a b c
// d e f
// g h i
// bad

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 3
// a b c
// d e f
// g h i
// ace

// Sample Output-2:
// ----------------
// false


// Sample Input-3:
// ---------------
// 3 3
// a b c
// d e f
// g h i
// add

// Sample Output-3:
// ----------------
// false


import java.util.*;

public class CanConstructWord{
    public static boolean dfs(char[][] arr, String s, int n, int m, int i, int j, int currIndex){
        if(currIndex == s.length()){
            return true;
        }
        if(i>=n || i<0 || j>=m || j<0 || arr[i][j] != s.charAt(currIndex)){
            return false;
        }
        return dfs(arr,s,n,m,i+1,j,currIndex+1) || dfs(arr,s,n,m,i,j+1,currIndex+1) || dfs(arr,s,n,m,i-1,j,currIndex+1) || dfs(arr,s,n,m,i,j-1,currIndex+1);
    }
    public static boolean canConstruct(char[][] arr, String s, int n, int m){
        boolean res = false;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(arr[i][j] == s.charAt(0)){
                    if(dfs(arr,s,n,m,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] arr = new char[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                arr[i][j] = sc.next().charAt(0);
            }
        }
        String s = sc.next();
        System.out.println(canConstruct(arr,s,n,m));
        sc.close();
    }
}