// Luke likes to construct and play with arrays. His dad gave him an array M of 
// length N and assigned him the following tasks to be done in order:
//  - reate continuous groups of size i from the array M (where i goes from 1 to N).
//  - For each group of size i, find the minimum value.
//  - Among all the minimums from that step, select the maximum.
//  - Add this selected value as the i-th element of a new array P.
//  - Repeat until i = N.

// Note: Use 1-based indexing for array P in logic.

// Input Format:
// -------------
// input1: Integer N – length of array M
// input2: Integer array M of length N

// Output Format:
// --------------
// Return the array P as output.

// Sample Input:
// -------------
// 3
// 1 2 3

// Sample Output:
// --------------
// 3 2 1


// Sample Input: 
// -------------
// 4
// 20 10 30 40

// Sample Output: 
// --------------
// 40 30 10 10

import java.util.*;
import java.util.LinkedList;
public class MaximumOfMinimums{
    public static int getMax(int[] arr, int n, int k){
        Deque<Integer> deque = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<n;i++){
            while(!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i>=k-1){
                max = Math.max(max,arr[deque.peekFirst()]);
            }
        }
        return max;
    }
    public static void getMaxOfGroups(int[] arr, int n){
        int[] newarr = new int[n+1];
        for(int i = 1;i<=n;i++){
            newarr[i] = getMax(arr,n,i);
        }
        for(int i = 1;i<=n;i++){
            System.out.println(newarr[i] + " ");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        getMaxOfGroups(arr,n);
        sc.close();
    }
}