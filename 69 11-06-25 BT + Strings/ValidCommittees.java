// A group of students is forming a tech club, and they are 
// interviewing K × K students. Each student has two skill levels:
//  - Mathematics (M)
//  - Physics (P)
// These skill levels range from 0 to K-1.

// They want to form committees of size N under the following 
// conditions:
//  - All members of a committee must have different Mathematics 
//    skill levels.
//  - All members must have different Physics skill levels.
//  - For any two students in the committee, the difference of their 
//    Math skills must not equal the difference of their Physics skills, 
//    i.e., |M1 - M2| != |P1 - P2|

// Input Format:
// -------------
// input1: Integer N – desired committee size
// input2: Integer K – number of skill levels for Math and Physics 
// (total students = K × K)

// Output Fromat:
// --------------
// An integer representing the number of valid committees of size N 
// that can be formed from the K × K students. Return the result 
// modulo 10⁹ + 7.

// Sample Input: 
// -------------
// 2
// 3

// Sample Output: 
// --------------
// 8

// Explanation:
// ------------
// Examples of some valid pairs:
// (0,0) and (1,2) → |0-1| = 1, |0-2| = 2 ✅
// (0,1) and (1,0) → |0-1| = 1, |1-0| = 1 ❌ (same diff → invalid)
// (0,0) and (2,1) → |0-2| = 2, |0-1| = 1 ✅

// 8 Valid Pairs: 1. (0,0) - (1,2)
// 2. (0,0) - (2,1)
// 3. (0,1) - (2,0)
// 4. (0,2) - (1,0)
// 5. (0,2) - (2,1)
// 6. (1,0) - (2,2)
// 7. (1,2) - (0,0)
// 8. (1,2) - (2,0)
             
// Sample Input: 
// -------------
// 2
// 2

// Sample Output: 
// --------------
// 0

// N-Queens Variation

import java.util.*;
public class ValidCommittees{
    public static int getValidCommittees(int n, int k){
        int[][] arr = new int[k][k];
        int[] count = {0};
        solve(arr,n,k,count,0);
        return count[0];
    }
    public static void solve(int[][] arr, int n, int k, int[] count, int curr){
        if(n==0){
            count[0]++;
            return;
        }
        if(curr>=k){
            return;
        }
        for(int i = 0;i<k;i++){
            if(isSafe(arr, curr, i, k)){
                arr[curr][i] = 1;
                solve(arr, n-1, k, count, curr+1);
                arr[curr][i] = 0;
            }
        }
        solve(arr, n, k, count, curr+1);
    }
    static boolean isSafe(int[][] arr, int row, int col, int k){
        for(int i = 0; i < row; i++){
            if(arr[i][col] == 1){
                return false;
            }
        }
        for(int i = row-1, j = col-1; i>=0 && j>=0; i--, j--){
            if(arr[i][j]==1){
                return false;
            }
        }
        for(int i = row-1, j = col+1; i>=0 && j<k; i--, j++){
            if(arr[i][j]==1){
                return false;
            }
        }
	    return true;
	}
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(getValidCommittees(n,k));
        sc.close();
    }
}