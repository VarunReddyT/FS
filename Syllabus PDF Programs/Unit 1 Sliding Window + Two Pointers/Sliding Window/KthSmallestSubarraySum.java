// Given an integer array nums of length n and an integer k, return the k-th smallest subarray 
// sum. A subarray is defined as a non-empty contiguous sequence of elements in an array. 
// A subarray sum is the sum of all elements in the subarray.
// Example 1:
// Input: nums = [2,1,3], k = 4
// Output: 3
// Explanation: The subarrays of [2,1,3] are:
//  [2] with sum 2
//  [1] with sum 1
//  [3] with sum 3
//  [2,1] with sum 3
//  [1,3] with sum 4
//  [2,1,3] with sum 6
// Ordering the sums from smallest to largest gives 1, 2, 3, 3, 4, 6. The 4th smallest is 3.
// Example 2:
// Input: nums = [3,3,5,5], k = 7
// Output: 10
// Explanation: The subarrays of [3,3,5,5] are:
//  [3] with sum 3
//  [3] with sum 3
//  [5] with sum 5
//  [5] with sum 5
//  [3,3] with sum 6
//  [3,5] with sum 8
//  [5,5] with sum 10
//  [3,3,5], with sum 11
//  [3,5,5] with sum 13
//  [3,3,5,5] with sum 16
// Ordering the sums from smallest to largest gives 3, 3, 5, 5, 6, 8, 10, 11, 13, 16. The 7th smallest is 
// 10.


import java.util.*;

public class KthSmallestSubarraySum {
    public int kthSmallestSubarraySum(int[] nums, int k) {
        // Initialize the left and right boundaries for the binary search.
        // Assume the smallest subarray sum is large, and find the smallest element of
        // the array.
        int left = Integer.MAX_VALUE, right = 0;
        for (int num : nums) {
            left = Math.min(left, num);
            right += num;
        }
        // Perform binary search to find the kth smallest subarray sum.
        while (left < right) {
            int mid = left + (right - left) / 2;
            // If there are more than k subarrays with a sum <= mid, move the right pointer.
            if (countSubarraysWithSumAtMost(nums, mid) >= k) {
                right = mid;
            } else {
                // Otherwise, move the left pointer.
                left = mid + 1;
            }
        }
        // The left pointer points to the kth smallest subarray sum.
        return left;
    }

    // Helper method to count the number of subarrays with a sum at most 's'.
    private int countSubarraysWithSumAtMost(int[] nums, int s) {
        int currentSum = 0, start = 0;
        int count = 0;
        for (int end = 0; end < nums.length; ++end) {
            currentSum += nums[end];
            // If the current sum exceeds 's', shrink the window from the left.
            while (currentSum > s) {
                currentSum -= nums[start++];
            }
            // Add the number of subarrays ending at index 'end' with a sum at most 's'.
            count += end - start + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter array elements size");
        int n = sc.nextInt();
        int array[] = new int[n];
        System.out.println("enter the elements");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("enter the kth samllest subarray size");
        int k = sc.nextInt();
        System.out.println(new KthSmallestSubarraySum().kthSmallestSubarraySum(array, k));
        sc.close();
    }
}