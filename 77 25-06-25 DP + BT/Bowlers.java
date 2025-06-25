// BCCI wants to select the group of bowlers for an upcoming test-series, 
// you want to choose the group with highest number of wickets, which is 
// the sum of wickets taken by all the bowlers in that group.

// However, the bowler group is not allowed to have any disputes. A dispute
// exists if a younger bowler has strictly highest wickets than an older bowler. 
// A dispute does not occur between bowlers of the same age.

// You are given information of N bowlers as two lists, wickets and ages, 
// where each wickets[i] and ages[i] represents the wickets and age of 
// the i-th bowler, respectively, return the highest number of wickets 
// of all possible bowler groups.


// Input Format:
// -------------
// Line-1: An integer N, number of bowlers.
// Line-2: N space separated integers, wickets[].
// Line-3: N space separated integers, ages[].

// Output Format:
// --------------
// An integer, highest number of wickets of all possible bowler groups.


// Sample Input-1:
// ---------------
// 4
// 5 3 5 6
// 3 5 4 2

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// It is best to choose 2 bowlers of age 3 and 4. 


// Sample Input-2:
// ---------------
// 5
// 5 3 5 6 3
// 2 5 4 2 1

// Sample Output-2:
// ----------------
// 14

// Explanation:
// ------------
// It is best to choose 3 bowlers of age 1 and 2. 
// Notice that you are allowed to choose multiple bowlers of the same age.

// Sample Input-3:
// ---------------
// 5
// 1 3 5 10 15
// 1 2 3 4 5

// Sample Output-3:
// ----------------
// 34

// Explanation:
// ------------
// You can choose all the bowlers.


import java.util.*;
public class Bowlers{
    static class Bowler{
        int w;
        int a;
        Bowler(int w,int a){
            this.w=w;
            this.a=a;
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int wic[] = new int[n];
        int ages[] = new int[n];
        for(int i=0;i<n;i++){
            wic[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            ages[i] = sc.nextInt();
        }
        List<Bowler> bowlers = new ArrayList<>();
        for(int i=0;i<n;i++){
            bowlers.add(new Bowler(wic[i],ages[i]));
        }
        Collections.sort(bowlers,(a,b)->a.a==b.a ? a.w-b.w : a.a-b.a);
        Integer dp[][] = new Integer[n][n+1];
        System.out.println(memo(wic,ages,dp,bowlers,0,-1));
        sc.close();
    }
    public static int memo(int[] wic,int[] ages,Integer dp[][], List<Bowler> bowlers,int idx,int prev){
        if(idx>=wic.length) return 0;
        if(dp[idx][prev+1]!=null) return dp[idx][prev+1];
        
        int nottake = memo(wic,ages,dp,bowlers,idx+1,prev);
        
        int take = 0;
        if(prev==-1 || bowlers.get(idx).w>=bowlers.get(prev).w){
            take += bowlers.get(idx).w + memo(wic,ages,dp,bowlers,idx+1,idx);
        }
        
        return dp[idx][prev+1] = Math.max(take,nottake);
    }
}