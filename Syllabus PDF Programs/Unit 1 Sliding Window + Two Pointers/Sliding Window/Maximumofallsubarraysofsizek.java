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


import java.util.*;
import java.util.LinkedList;

public class Maximumofallsubarraysofsizek {
    // A Dequeue (Double ended queue) based method for printing maximum element of
    // all
    // subarrays of size K
    public static void printMax(int arr[], int N, int K) {
        // Create a Double Ended Queue, Qi that will store indexes of array elements The
        // queue will
        // store indexes of useful elements in every window and it will maintain
        // decreasing order of
        // values from front to rear in Qi, i.e., arr[Qi.front[]] to arr[Qi.rear() are
        // sorted in decreasing
        // order
        Deque<Integer> Qi = new LinkedList<Integer>();
        int i;
        for (i = 0; i < K; ++i) {
            while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();
            Qi.addLast(i);
        }
        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for (i = K; i < N; ++i) {
            // The element at the front of the queue is the largest element of previous
            // window, so print it
            System.out.print(arr[Qi.peek()] + " ");
            // Remove the elements which are out of this window
            while ((!Qi.isEmpty()) && Qi.peek() <= i - K)
                Qi.removeFirst();
            // Remove all elements smaller than the currently being added element (remove
            // useless elements)
            while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();

            // Add current element at the rear of Qi
            Qi.addLast(i);
        }
        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        printMax(a, n, k);
        sc.close();
    }
}