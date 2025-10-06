// In a team of N players, each player's jersey has a number printed on it.
// Jersey numbers may be repeated.

// You will be given the list of jersey numbers of N players. 
// You need to find out  number of subgroups of jersey numbers 
// that are in strictly ascending order.

// A subgroup is defined as the continuous list of jersey numbers.

// Input Format:
// -------------
// Line-1: An integer N, number of players
// Line-2: N space separated integers, jersey numbers

// Output Format:
// --------------
// Print a long value as the result.


// Sample Input-1:
// ---------------
// 6
// 2 4 6 5 5 7

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// The strictly ascending subgroups are the following:
// - Subgroups of length 1: [2], [4], [6], [5], [5], [7].
// - Subgroups of length 2: [2,4], [4,6], [5,7].
// - Subgroups of length 3: [2,4,6].
// The total number of subgroups is 6 + 3 + 1 = 10.


// Sample Input-2:
// ---------------
// 5
// 2 4 6 8 10

// Sample Output-2:
// ----------------
// 15

import java.util.*;

public class SubGroupsCount{
    public static int getGroupsCount(int[] arr, int n){
        int res = 0;
        int temp = 0;
        for(int i = 1;i<n;i++){
            if(arr[i] > arr[i-1]){
                temp++;
            }
            else{
                temp = 0;
            }
            res += temp;
        }
        res += n;
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(getGroupsCount(arr,n));
        sc.close();
    }
}