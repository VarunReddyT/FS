// Venkatadri is a maths teacher.
// He is teaching matrices to his students.
// He is given a matrix of size m*n, and it contains only positive numbers.
// He has given a task to his students to find the special matrix, 
// in the iven matrix A[m][n].
// A special matrix has following property:
// 	- The sum of elements in each row, each column and the two diagonals are equal.
// 	- Every 1*1 matrix is called as a special matrix.
// 	- The size of the special matrix should be a square, i.e., P*P.

// Your task is to help the students to find the speical matrix  with max size P.


// Input Format:
// -------------
// Line-1: Two space separated integers M and N, size of the matrix.
// Next M lines: N space separated integers m and n.

// Output Format:
// --------------
// Print an integer, maximum size P of the special matrix.


// Sample Input-1:
// ---------------
// 5 5
// 7 8 3 5 6
// 3 5 1 6 7
// 3 5 4 3 1
// 6 2 7 3 2
// 5 4 7 6 2

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// The special square is:
// 5 1 6
// 5 4 3
// 2 7 3


// Sample Input-2:
// ---------------
// 4 4
// 7 8 3 5
// 3 2 1 6
// 3 2 3 3
// 6 2 3 3

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// The special square is:
// 3 3
// 3 3

import java.util.*;

public class SpecialMatrix{
    public static boolean isSafe(int[][] arr, int i, int j, int max){
        int sum = 0;
        for(int k = j;k<j+max;k++){
            sum += arr[i][k];
        }
        for(int k = i+1;k<i+max;k++){
            int rowSum = 0;
            for(int l = j;l<j+max;l++){
                rowSum += arr[k][l];
            }
            if(rowSum != sum){
                return false;
            }
        }
        for(int k = j;k<j+max;k++){
            int colSum = 0;
            for(int l = i;l<i+max;l++){
                colSum += arr[l][k];
            }
            if(colSum != sum){
                return false;
            }
        }
        int diagonalSum = 0;
        for(int k = 0;k<max;k++){
            diagonalSum += arr[i+k][j+k];
        }
        if(diagonalSum != sum){
            return false;
        }
        diagonalSum = 0;
        for(int k = 0;k<max;k++){
            diagonalSum += arr[i+k][j+max-1-k];
        }
        if(diagonalSum != sum){
            return false;
        }
        return true;
    }
    public static int getMaxSpecialMatrix(int[][] arr, int m, int n){
        int maxSide = Math.min(m,n);
        for(int i = maxSide; i>=1;i--){
            for(int j = 0;j<=m-i;j++){
                for(int k = 0;k<=n-i;k++){
                    if(isSafe(arr,j,k,i)){
                        return i;
                    }
                }
            }
        }
        return 1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(getMaxSpecialMatrix(arr,m,n));
        sc.close();
    }
}