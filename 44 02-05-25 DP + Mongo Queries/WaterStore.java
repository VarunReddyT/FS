/*
A Kid built a structure using building blocks,
by placing the building-blocks adjacent to each other.

A building-block is a vertical alignment of blocks.
	                            ___
here one block each represents  as |_|.

The following structure made up of using building blocks

                          ___
                      __||    ___
                     |||w||__              ___
                  __|||||| w   w  w |_|
              __|||||||_w||w||___________

               0  1   3   4   2   3    2   0   1   0   2

Once the structure is completed, kid pour water(w) on it.

You are given a list of integers, heights of each building-block in a row.
 Now your task How much amount of water can be stored by the structure.

 Input Format:
 -------------
 Space separated integers, heights of the blocks in the structure.

Output Format:
 --------------
 Print an integer,

Sample Input:
-------------
 0 1 3 4 2 3 2 0 1 0 2

Sample Output:
--------------
6

Explanation:
 -----------
In the above structure,  6 units of water (w represents the water in the structure)
can be stored.

 */

 import java.util.*;

public class WaterStore {
    public static int getWater(int[] arr) {
        int n = arr.length;
        if (n == 0){
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = arr[0];
        rightMax[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            trappedWater += Math.min(leftMax[i], rightMax[i]) - arr[i];
        }

        return trappedWater;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] arr = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getWater(arr));
        sc.close();
    }

}