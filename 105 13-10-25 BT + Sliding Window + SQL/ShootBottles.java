// You are playing a shooting game.
// There are N bottles hanging to a rod, i-th bottle numbered bottle[i].

// If you shoot i-th bottle, you will get bottle[i-1]*bottle[i]*bottle[i+1] points.
// The more points you score, the more rewards you can win.

// Your task is to score the maximum points by shooting all the ballons wisely.

// Note: After you shoot bottle at i-th index, bottles at i-1 and i+1 positions
// become adjacent. if there is no existing 'i-1' or 'i+1' positions for selected bottle.
// you can assume that bottle[i-1] = bottle[i+1] = 1.

// Input Format:
// -------------
// N space separated integers bottles[].

// Output Format:
// --------------
// Print an integer, maximum points you can get.


// Sample Input:
// ---------------
// 3 1 5 8

// Sample Output:
// ----------------
// 167

// Explanation:
// ------------
// Given bottles = [3, 1, 5, 8] 
// position 	points
// --------------------
// 1				3*1*5
// 5				3*5*8
// 3				1*3*8
// 8				1*8*1
// --------------------
// Total = 167


// Sample Input:
// ---------------
// 2 1 3 5 4

// Sample Output:
// ----------------
// 102

// Explanation:
// ------------
// Given bottles = [2, 1, 3, 5, 4] 

// position 	points
// --------------------
// 5				3*5*4
// 1				2*1*3
// 3				2*3*4
// 2				1*2*4
// 4				1*4*1
// --------------------
// Total = 102


import java.util.*;

public class ShootBottles{
    public static int solve(int[] arr){
        ArrayList<Integer> ar = new ArrayList<>();
        for(int i : arr){
            ar.add(i);
        }
        int[] max = new int[]{0};
        bt(ar,arr.length,max,0);
        return max[0];
    }
    public static void bt(ArrayList<Integer> arr, int n, int[] max, int currSum){
        if(arr.size() == 0){
            max[0] = Math.max(max[0],currSum);
            return;
        }
        for(int i = 0;i<arr.size();i++){
            int l = (i == 0) ? 1 : arr.get(i-1);
            int r = (i == arr.size()-1) ? 1 : arr.get(i+1);
            int prod = l*arr.get(i)*r;
            int temp = arr.get(i);
            arr.remove(i);
            bt(arr,n,max,currSum + prod);
            arr.add(i,temp);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        int[] arr = new int[inp.length];
        for(int i = 0;i<inp.length;i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(solve(arr));
        sc.close();
    }
}