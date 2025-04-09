// b) Write a JAVA Program to find shortest sub array with sum at least K
// Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K. If 
// there is no non-empty subarray with sum at least K, return -1.
// Example 1:
// Input: A = [1], K = 1
// Output: 1
// Example 2:
// Input: A = [1,2], K = 4
// Output: -1
// Example 3:
// Input: A = [2,-1,2], K = 3
// Output: 3 
// Note:
// 1 <= A.length <= 50000
// -10 ^ 5 <= A[i] <= 10 ^ 5
// 1 <= K <= 10 ^ 9

import java.util.*;
import java.util.LinkedList;

public class ShortestSubarrayWithAtleastKSum_1b {
    public static int shortestSubarray(int[] arr, int k, int n){
        int res = n+1;
        long[] p = new long[n+1];
        for(int i = 0;i<n;i++){
            p[i+1] = p[i] + (long)arr[i];
        }
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0;i<p.length;i++){
            while(!deque.isEmpty() && p[i] <= p[deque.getLast()]){
                deque.removeLast();
            }
            while(!deque.isEmpty() && p[i] >= p[deque.getFirst()] + k){
                res = Math.min(res, i-deque.removeFirst());
            }
            deque.addLast(i);
        }
        return res < n+1 ? res : -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(shortestSubarray(arr, k, n));
        sc.close();
    }
}