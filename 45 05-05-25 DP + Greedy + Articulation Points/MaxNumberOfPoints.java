
// Tejaswi playing a game. Game has a display of N numbers in a row, nums[].
// Tejaswi has to pick the numbers one after the other.

// Once Tejaswi picks a number num[i], she will score num[i] points and 
// the number will be disappared on the board, simultaneously all the numbers 
// having the value num[i]+1 or num[i]-1 also disappears. 

// Tejaswi has to select the numbers, such that no more numbers will be left over 
// on the board.

// Tejaswi wants to score maximum number of points.

// Initially Tejaswi has 0 points with her.
// Can you help Tejaswi to score maximum number of points.

// Input Format:
// -------------
// Line-1 -> an integers N, number of numbers on the game board.
// Line-2 -> N space separated integers, numbers on the game board nums[].

// Output Format:
// --------------
// Print an integer as result, maximum score she can get.


// Sample Input-1:
// ---------------
// 3
// 4 5 3

// Sample Output-1:
// ----------------
// 8

// Explanation: 
// ------------
// Pick a number 5 to score 5 points, simultaneously 4 is disappeared from display.
// Then, pick number 3 to score 3 points.
// Totally 8 is the score.


// Sample Input-2:
// ---------------
// 6
// 4 4 5 5 5 6

// Sample Output-2:
// ----------------
// 15

// Explanation: 
// -------------
// Delete 5 to earn 5 points, deleting both 4's and the 6.
// Then, delete 5 again to earn 5 points, and 5 again to earn 5 points.
// Totally 15 is the score.

// Pick a number 5 to score 5 points, simultaneously all 4's and 6 are disappeared 
// from display. Then, again pick the number 5 to score 5 points.
// And again pick the number 5 to score 5 points. Totally 15 is the score.

import java.util.*;

public class MaxNumberOfPoints{
    public static int getMaxPoints(int[] arr, int n){
        // HashMap<Integer,Integer> map = new HashMap<>();
        // for(int i : arr){
        //     map.put(i,map.getOrDefault(i,0)+1);
        // }
        // List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        // list.sort((e1,e2)->{
        //     int cmp = Integer.compare(e2.getKey()*e2.getValue(), e1.getKey()*e1.getValue());
        //     if(cmp == 0){
        //         return Integer.compare(e2.getKey(),e1.getKey());
        //     }
        //     return cmp;
        // });
        // HashSet<Integer> set = new HashSet<>();
        // int count = 0;
        // for(Map.Entry<Integer,Integer> entry : list){
        //     int number = entry.getKey();
        //     if(!set.contains(number)){
        //         count += number*entry.getValue();
        //         set.add(number);
        //         set.add(number-1);
        //         set.add(number+1);
        //     }
        // }
        // return count;
        int max = Integer.MIN_VALUE;
        for(int i : arr){
            max = Math.max(max,i);
        }
        int[] freq = new int[max+1];
        for(int i : arr){
            freq[i]++;
        }
        int[] dp = new int[max+1];
        dp[0] = 0;
        dp[1] = freq[1];
        for(int i = 2;i<=max;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+freq[i]*i);
        }
        return dp[max];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(getMaxPoints(arr,n));
        sc.close();
    }
}