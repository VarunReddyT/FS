// A group of researchers is analyzing satellite imagery of agricultural fields, 
// represented by a grid of land sections. Each section is marked either as 
// fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
// need to identify the largest rectangular area consisting exclusively of fertile 
// land sections.

// Given a binary grid representing the land (1 for fertile and 0 for infertile), 
// your task is to compute the area of the largest rectangle consisting entirely 
// of fertile land sections.

// Input Format:
// -------------
// Line-1: Two integers rows(r) and columns(c) of grid.
// Next r lines: c space separated integers, each row of the grid.

// Output Format:
// --------------                         
// Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

// Example:
// --------
// input=
// 4 5
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// output=
// 6

// import java.util.*;

// public class Solution{
//     public static int getFertileSection(int[][] arr, int n, int m){
//         int[][] dp = new int[n+1][m+1];
//         for(int i = 0;i<)
//     }
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int m = sc.nextInt();
//         int[][] arr = new int[n][m];
//         for(int i = 0;i<n;i++){
//             for(int j = 0;j<m;j++){
//                 arr[i][j] = sc.nextInt();
//             }
//         }
//         System.out.println(getFertileSection(arr, n, m));
//     }
// }

import java.util.*;

public class LargestAreaRectangle{
    public static int getRectangles(int[][] arr, int n, int m){
        int[][] pref = new int[n+1][m];
        
        for(int i = 1;i<=n;i++){
            for(int j = 0;j<m;j++){
                pref[i][j] += pref[i-1][j] + arr[i-1][j];
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 1;i<=n;i++){
            for(int j = i;j<=n;j++){
                int temp = 0;
                int count = 0;
                for(int k = 0;k<m;k++){
                    if(pref[j][k]-pref[i-1][k] == (j-i+1)){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                    temp = Math.max(temp,count);
                }
                max = Math.max(max,temp*(j-i+1));
            }
        }
        return max;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(getRectangles(arr,n,m));
        sc.close();
    }
}