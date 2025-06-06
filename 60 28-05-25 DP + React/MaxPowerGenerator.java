// You are the lead engineer in a high-tech robot factory with 'n production units', 
// each assigned a number from 0 to n - 1. Every unit contains a specialized 
// 'robot component', and the efficiency level of each component is represented by 
// an array components, where components[i] is the efficiency level of the i-th component.

// As part of the factory shutdown protocol, you need to deactivate all the units 
// one by one. However, the deactivation process is tricky — when you shut down the 
// i-th unit, it triggers a chain reaction with its neighboring units, generating:

//     components[i - 1] * components[i] * components[i + 1] power units

// If i - 1 or i + 1 falls outside the range of the array, assume there’s a dummy 
// component with efficiency level 1 installed for safety compliance.

// Your objective is to maximize the total power generated by planning the shutdown
// order strategically.

// Example 1:
// Input=
// 2 1 3 5
// output=
// 51

// Explanation:
// Shutdown order:
// [2,1,3,5] → [2,3,5] → [2,5] → [5] → []
// Power generated:
// 2*1*3 + 2*3*5 + 1*2*5 + 1*5*1 = 51


// Example 2:
// Input= 
// 8 9
// Output=
// 81

// Explanation:
// Deactivate both units in any order: 1*8*9 + 1*9*1 = 81

// Constraints:

// -> n == components.length
// -> 1 <= n <= 300
// -> 0 <= components[i] <= 100

import java.util.*;

public class MaxPowerGenerator {
    public static int solve(int[][] dp, int[] arr, int n, int i, int j){
        if(i==j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int res = 0;
        for(int k = i;k<j;k++){
            int totalPower = solve(dp,arr,n,i,k) + solve(dp,arr,n,k+1,j) + arr[i-1]*arr[k]*arr[j];
            res = Math.max(res,totalPower);
        }
        return dp[i][j] = res;
    }
    public static int getMaxPower(int[] arr){
        int n = arr.length;
        int newArr [] = new int[n+2];
        for(int i = 1 ; i<n+1;i++){
            newArr[i] = arr[i-1];
        }
        newArr[0] = 1;
        newArr[n+1] = 1;
        int[][] dp = new int[n+2][n+2];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return solve(dp,newArr,n,1,n+1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] arr = new int[input.length];
        for(int i = 0;i<input.length;i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getMaxPower(arr));
        sc.close();
    }
}