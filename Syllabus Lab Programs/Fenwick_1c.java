// c) Write a JAVA Program to implement Fenwick Tree
// Malika taught a new fun time program practice for Engineering Students. As a part of this she 
// has given set of numbers, and asked the students to find the gross value of numbers between 
// indices S1 and S2 (S1<=S2), inclusive. Now it’s your task to create a method sumRange(S1,S2) 
// which return the sum of numbers between the indices S1 and S2, both are inclusive.
// Input Format:
// Line-1: An integer n, size of the array(set of numbers). 
// Line-2: n space separated integers.
// Line-3: Two integers s1 and s2, index positions where s1<=s2.
// Output Format:
// An integer, sum of integers between indices(s1, s2).
// Sample Input-1:
// 8
// 1 2 13 4 25 16 17 8
// 2 6
// Sample Output-1:
// 75
// Constraints:
// 1 <= nums.length <= 3 * 104
// -100 <= nums[i] <= 100
// 0 <= index < nums.length
// -100 <= val <= 100
// 0 <= left <= right < nums.length
// At most 3 * 104 calls will be made to update and sumRange.

import java.util.*;

public class Fenwick_1c{
    int[] BIT;
    int n;
    int[] nums;
    Fenwick_1c(int[] arr){
        n = arr.length;
        this.nums = arr;
        BIT = new int[n+1];
        for(int i = 0;i<n;i++){
            update(i+1,nums[i]);
        }
    }
    public void update(int index, int value){
        while(index<=n){
            BIT[index] += value;
            index += (index & -index);
        }
    }
    public int rangeSum(int left, int right){
        return query(right) - query(left-1);
    }
    public int query(int index){
        int sum = 0;
        while(index > 0){
            sum += BIT[index];
            index -= (index & -index);
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        Fenwick_1c ft = new Fenwick_1c(arr);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(ft.rangeSum(a+1,b+1));
        sc.close();
    }
}