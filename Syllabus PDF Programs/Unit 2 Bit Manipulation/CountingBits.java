// 1. Counting Bits
// Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <=n), ans[i]
// is the number of 1's in the binary representation of i.
// Input: n = 2
// Output: [0,1,1]
// Explanation:
// 0 --> 0
// 1 --> 1
// 2 --> 10
// Input: n = 5
// Output: [0,1,1,2,1,2]
// Explanation:
// 0 --> 0
// 1 --> 1
// 2 --> 10
// 3 --> 11
// 4 --> 100
// 5 --> 101

import java.util.*;

public class CountingBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] result = new int[n+1];
        result = countBits(n);
        System.out.println(Arrays.toString(result));
        sc.close();
    }

    public static int[] countBits(int n) {
        int[] r = new int[n + 1];
        r[0] = 0;
        for(int i = 1; i <= n; i++) {
            int x = i;
            int count = 0;
            while(x > 0) {
                count += x & 1;
                x >>= 1;
            }
            r[i] = count;
        }
        return r;
    }
}