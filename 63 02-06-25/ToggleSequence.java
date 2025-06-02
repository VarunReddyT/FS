// For the given a list of integers, Your task to is find out, the length of the 
// longest subsequence that is a toggle sequence.

// Toggle Sequence means, the difference between the consecutive numbers
// are alternate positive and negative.

// For Example:
// Given list of integers = 1 3 2 5 4 
// the consecutive differences are [ 2, -1, 3, -1], 
// the differences are alternate +ve and -ve.
// So, complete list is a toggle sequence.

// If the list of integers = 1 3 2 4 5,
// the consecutive differences are [ 2, -1, 2, 1], not alternate +ve and -ve.
// Not a toggle sequence.

// Note: A sequence with fewer than two elements is a toggle sequence.

// Input Format:
// -------------
// Space separated Integers, List

// Output Format:
// --------------
// Print the length of the longest toggle sequence


// Sample Input-1:
// ---------------
// 1 7 4 9 2 5

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// Given list of integers = 1 7 4 9 2 5
// the consecutive differences are [ 6, -3, 5, -7, 3], 
// the differences are alternate +ve and -ve.
// So, complete list is a toggle sequence.

// Sample Input-2:
// ---------------
// 1 5 4 3 7 9 10

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// Given list of integers = 1 5 4 3 7 9 10
// There are several subsequences that achieve this length.
// One is [1 5 4 7] with differences (4, -1, 3).

import java.util.*;

public class ToggleSequence{
    public static int getMaxSubArray(int[] arr, int n){
        int pos = 1;
        int neg = 1;
        for(int i = 1;i<n;i++){
            if((arr[i]-arr[i-1]) > 0){
                pos = neg+1;
            }
            else{
                neg = pos+1;
            }
        }
        return Math.max(pos,neg);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] arr = new int[input.length];
        for(int i = 0;i<input.length;i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getMaxSubArray(arr,arr.length));
        sc.close();
    }
}

// Dry Run
// 1 5 4 3 7 9 10

// pos = 1, neg = 1
// pos = 2, neg = 1
// pos = 2, neg = 3
// pos = 2, neg = 3
// pos = 4, neg = 3
// pos = 4, neg = 3
// pos = 4, neg = 3
