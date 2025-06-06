
// You are given some tokens printed with unique numbers on it and an integer T.
// Your task is to find the least number of tokens that you need to make up the 
// value T, by adding the numbers printed on all the tokens. 
// If you cannot make the value T, by any combination of the tokens, return -1.

// NOTE: Assume that you have unlimited set of tokens of each number type.

// Input Format:
// -------------
// Line-1: Space separated integers tokens[], number printed on tokens.
// Line-2: An integer T.

// Output Format:
// --------------
// Print an integer, minimum number of tokens to make the value T.


// Sample Input-1:
// ---------------
// 1 2 5
// 11

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// 5+5+1 = 11


// Sample Input-2:
// ---------------
// 2 4
// 15

// Sample Output-2:
// ----------------
// -1


import java.util.*;
public class TargetSum{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        int t = sc.nextInt();
        int n = s.length;
        int arr[] = new int[n];
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(s[i]);
            al.add(arr[i]);
        }
        // int count[] = {Integer.MAX_VALUE};
        // Collections.sort(al,Collections.reverseOrder());
        // bt(al,count,t,0,n,0,0);
        // int dp[][] = new int[n+1][target+1];
        // for(int i=1;i<=n;i++){
        //     for(int j=1;j<=target;j++){
        //         if(){
                    
        //         }else{
                    
        //         }
        //     }
        // }
        // // System.out.println(count[0]==Integer.MAX_VALUE?-1:count[0]);
        // System.out.println(dp[n][target]);
        
        // int[] dp = new int[t + 1];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        // dp[0] = 0;

        // for (int i = 1; i <= t; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (i - arr[j] >= 0 && dp[i - arr[j]] != Integer.MAX_VALUE) {
        //             dp[i] = Math.min(dp[i], 1 + dp[i - arr[j]]);
        //         }
        //     }
        // }
        // System.out.println(dp[t] == Integer.MAX_VALUE ? -1 : dp[t]);
        
        // Memoization
        int[] dp = new int[t + 1];
        Arrays.fill(dp, -1);
        int ans = memo(arr, t, dp);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        sc.close();
    }
    
    public static int memo(int[] arr, int target, int[] dp) {
        if (target == 0) return 0;
        if (dp[target] != -1) return dp[target];

        int min = Integer.MAX_VALUE;
        for (int coin : arr) {
            if (target - coin >= 0) {
                int res = memo(arr, target - coin, dp);
                if (res != Integer.MAX_VALUE)
                    min = Math.min(min, 1 + res);
            }
        }
        return dp[target] = min;
    }
    
    public static void bt(ArrayList<Integer> al,int[] count,int target,int curr,int n,int step,int index){
        if(curr==target){
            count[0] = Math.min(count[0],step);
            return;
        }
        else if(curr>target) return;
        for(int i=index;i<al.size();i++){
            if(step+1<count[0]){
                bt(al,count,target,curr+al.get(i),n,step+1,i);
            }
            else{
                return;
            }
        }
    }
}