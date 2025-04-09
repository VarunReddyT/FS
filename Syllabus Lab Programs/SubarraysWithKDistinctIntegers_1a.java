// a) Write a JAVA Program to find Subarrays with K Different integers
// Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A 
// good if the number of different integers in that subarray is exactly K. (For example, [1,2,3,1,2] has 
// 3 different integers: 1, 2, and 3.)
// Return the number of good subarrays of A.
// Example 1:
// Input: A = [1,2,1,2,3], K = 2
// Output: 7
// Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1],
// [2,1,2], [1,2,1,2]
// Example 2:
// Input: A = [1,2,1,3,4], K = 3
// Output: 3
// Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], 
// [1,3,4]. Note:
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length
// 1 <= K <= A.length

import java.util.*;

public class SubarraysWithKDistinctIntegers_1a {
    public static int getSubarraysCount(int[] arr, int k, int n) {
        return helper(arr,k,n) - helper(arr,k-1,n);
    }
    public static int helper(int[] arr, int k, int n){
        int count = 0;
        int i = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int j = 0;j<n;j++){
            if (map.getOrDefault(arr[j], 0) == 0){
                k--;
            }
            map.put(arr[j],map.getOrDefault(arr[j],0)+1);
            while(k<0){
                map.put(arr[i], map.get(arr[i])-1);
                if(map.get(arr[i]) == 0){
                    k++;
                }
                i++;
            }
            count += j-i+1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(getSubarraysCount(arr, k, n));
        sc.close();
    }
}