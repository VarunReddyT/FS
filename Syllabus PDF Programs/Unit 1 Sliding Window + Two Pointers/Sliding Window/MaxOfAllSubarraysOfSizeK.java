// You are given an array of integers nums, there is a sliding window of size k which is moving 
// from the very left of the array to the very right. You can only see the k numbers in the window. 
// Each time the sliding window moves right by one position.Return the max sliding window.
// Example 1:
// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation: 
// Window position Max
// --------------- -----
// [1 3 -1] -3 5 3 6 7 3
// 1 [3 -1 -3] 5 3 6 7 3
// 1 3 [-1 -3 5] 3 6 7 5
// 1 3 -1 [-3 5 3] 6 7 5
// 1 3 -1 -3 [5 3 6] 7 6
// 1 3 -1 -3 5 [3 6 7] 7
// Example 2:
// Input: nums = [1], k = 1
// Output: [1]

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MaxOfAllSubarraysOfSizeK {
    private static int[] maxofAllSubarray(int[] a, int k) {
        int n = a.length;
        int[] maxOfSubarrays = new int[n - k + 1];
        int idx = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            q.add(a[windowEnd]);
            if (windowEnd - windowStart + 1 == k) {
                // We've hit the window size. Find the maximum in the current window and Slide
                // the window ahead
                int maxElement = q.peek();
                maxOfSubarrays[idx++] = maxElement;
                if (maxElement == a[windowStart]) {
                    // Discard a[windowStart] since we are sliding the window now. So it is going
                    // out
                    // of the window.
                    q.remove();
                }
                windowStart++; // Slide the window ahead
            }
        }
        return maxOfSubarrays;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter array elements size");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("enter the elements");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("enter the subarray size");
        int k = sc.nextInt();
        sc.close();
        int[] result = maxofAllSubarray(a, k);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}