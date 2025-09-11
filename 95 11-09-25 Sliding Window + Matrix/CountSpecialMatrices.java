// Venkatesh is a maths teacher.
// He is teaching matrices concept to his students.
// He is given a matrix of size m*n, and it contains only positive numbers.
// He has given a task to his students to find the number of special matrices 
// in the given matrix A[m][n].

// A special matrix has following property:
// 	- The size of matrix should be 3*3,
// 	- The sum of elements in each row, each column and 
// 	  the two diagonals are equal.
// 	- The 3*3 matrix should contains all the numbers from 1 to 9.
	
// Your task is to help the students to find the number of speical matrices
// in the given matrix.

// Input Format:
// -------------
// Line-1: Two space separated integers M and N, size of the matrix.
// Next M lines: N space separated integers m and n.

// Output Format:
// --------------
// Print an integer, number of the special matrices.


// Sample Input-1:
// ---------------
// 4 5
// 6 8 1 6 8
// 7 3 5 7 3
// 2 4 9 2 4
// 6 8 1 6 8

// Sample Output-1:
// ----------------
// 1

// Explanation:
// ------------
// The special square is:
// 8 1 6
// 3 5 7
// 4 9 2


// Sample Input-2:
// ---------------
// 3 5
// 2 7 6 7 2
// 9 5 1 5 9
// 4 3 8 3 4

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// The special squares are:
// 2 7 6
// 9 5 1
// 4 3 8
// -----
// 6 7 2
// 1 5 9
// 8 3 4


import java.util.*;

public class CountSpecialMatrices{
    public static boolean isSpecial(int[][] mat, int i, int j, int n, int m){
        Set<Integer> set = new HashSet<>();
        for(int row = i;row<i+3;row++){
            for(int col = j;col<j+3;col++){
                set.add(mat[row][col]);
            }
        }
        for(int k = 1;k<=9;k++){
            if(!set.contains(k)){
                return false;
            }
        }
        int rowSum = 0;
        int colSum = 0;
        for(int col = j;col<j+3;col++){
            rowSum += mat[i][col];
        }
        for(int row = i+1;row<i+3;row++){
            int tempRowSum = mat[row][j] + mat[row][j+1] + mat[row][j+2];
            if(tempRowSum != rowSum){
                return false;
            }
        }
        for(int row = i;row<i+3;row++){
            colSum += mat[row][j];
        }
        for(int col = j+1;col<j+3;col++){
            int tempColSum = mat[i][col] + mat[i+1][col] + mat[i+2][col];
            if(tempColSum != colSum){
                return false;
            }
        }
        if(rowSum != colSum){
            return false;
        }
        int diagonalSum = mat[i][j] + mat[i+1][j+1] + mat[i+2][j+2];
        if(diagonalSum != rowSum){
            return false;
        }
        int tempDiagonalSum = mat[i][j+2] + mat[i+1][j+1] + mat[i+2][j];
        if(tempDiagonalSum != diagonalSum){
            return false;
        }
        return true;
    }
    public static int getSpecialMatrices(int[][] mat, int n, int m){
        if(n < 3 || m < 3){
            System.out.println(0);
            return 0;
        }
        int res = 0;
        for(int i = 0;i<n-2;i++){
            for(int j = 0;j<m-2;j++){
                if(isSpecial(mat,i,j,n,m)){
                    res++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] mat = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(getSpecialMatrices(mat,n,m));
        sc.close();
    }
}