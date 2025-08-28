// You have given a bulb grid, where the bulb which is turned ON is indicated 
// with 1, and turned OFF is indicated with 0.

// You are allowed to perform an operation:
//     - Select a row/column in the buld grid, and toggle the bulbs,
//     the bulbs which are turned ON will be truned OFF and the bulbs which are 
//     turned OFF will be turned ON.

// Your task is to find the highest possible sum of all the binary equivalents 
// of each row in the bulb grid, after performing the above operation any 
// number of times on the bulb grid.


// Input Format:
// -------------
// Line-1: Two space separated integers, M and N
// Next M lines: N space separated integers, grid[][]

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 3 5
// 0 1 1 1 1 
// 1 0 1 1 1 
// 0 0 0 0 1 

// Sample Output-1:
// ----------------
// 78

// Explanation:
// ------------
// Given grid is 
// 0 1 1 1 1
// 1 0 1 1 1
// 0 0 0 0 1

// Perform operation on row-0 and row-2
// 1 0 0 0 0
// 1 0 1 1 1
// 1 1 1 1 0

// Perform operation on col-1 and col-4
// 1 1 0 0 1 = 25
// 1 1 1 1 0 = 30
// 1 0 1 1 1 = 23
// So, now the total value of Binary Equivalent is 78


// Sample Input-2:
// ---------------
// 2 3
// 0 1 0
// 0 0 1

// Sample Output-2:
// ----------------
// 11


import java.util.*;

public class MaxBinaryFlip{
    public static int getMaxBinary(int[][] arr, int n, int m){
        for(int i = 0;i<n;i++){
            if(arr[i][0] == 0){
                for(int j = 0;j<m;j++){
                    arr[i][j] = 1 - arr[i][j];
                }
            }
        }
        for(int j = 1;j<m;j++){
            int ones = 0;
            int zeroes = 0;
            for(int i = 0;i<n;i++){
                if(arr[i][j] == 1){
                    ones++;
                }
                else{
                    zeroes++;
                }
            }
            if(zeroes > ones){
                for(int i = 0;i<n;i++){
                    arr[i][j] = 1 - arr[i][j];
                }
            }
        }
        int res = 0;
        for(int i = 0;i<n;i++){
            int curr = m-1;
            int temp = 0;
            for(int j = 0;j<m;j++){
                if(arr[i][j] == 1){
                    temp += (int)(Math.pow(2,curr));
                }
                curr--;
            }
            res += temp;
        }
        return res;
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
        System.out.println(getMaxBinary(arr,n,m));
        sc.close();
    }
}