// The Kakatiyas had built a very elaborate dam(reservoir) system consisting of 
// rows and rows of dams, all dams have the same capacity X. 

// The first row has one dam, second row has two dams and 
// third row has three dams and so on. like as below(V -> dam) 
// 				 V
// 				V V
//                V V V
// 	        and so on..
// There are altogether 100 rows. Water is drawn from the river at X liters each time.
// The way water flows is that,
// - After the first drawing of water the dam on the first row is filled
// - After the second draw from the river, the dams in the second row are half-filled each.
// - After the third draw from the river, both the dams in the second row fully filled.
// - After the fourth draw from the river, the three dams in the third row are filled 
// to an extent of 1/4, 1/2, 1/4.

// Given N draws from the river, determine how full the jth dam in the ith row.

// The row is number from (0,0) onwards,
// 	The first row is row =0, dam = 0
// 	The second row is row =1, dams are 0 and 1. 
// 	so on...

// Input Format:
// -----------------
// Three space seperated integers, N, i, j

// Output Format:
// ------------------
// Print a double value as result


// Sample Input-1:
// -------------------
// 2 1 1

// Sample Output-1:
// ---------------------
// 0.5


// Sample Input-2:
// -------------------
// 4 2 2

// Sample Output-2:
// ---------------------
// 0.25

import java.util.*;

public class DamQuantity{
    public static double getQuantity(int n, int row, int col){
        double[][] mat = new double[101][101];
        mat[0][0] = n;
        for(int i = 0;i<100;i++){
            for(int j = 0;j<=i;j++){
                if(mat[i][j] > 1.0){
                    double overflow = mat[i][j] - 1.0;
                    mat[i][j] = 1.0;
                    mat[i+1][j] += overflow/2.0;
                    mat[i+1][j+1] += overflow/2.0;
                }
            }
        }
        return mat[row][col];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i = sc.nextInt();
        int j = sc.nextInt();
        System.out.println(getQuantity(n,i,j));
        sc.close();
    }
}