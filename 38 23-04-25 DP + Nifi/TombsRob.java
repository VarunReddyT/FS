// You are a stealthy archaeologist exploring a circular ring of ancient tombs 
// located deep within a jungle. Each tomb holds a certain number of precious 
// artifacts. 
// However, these tombs are protected by an ancient magical curse: 
// if you disturb two adjacent tombs during the same night, the entire ring 
// activates a trap that seals you in forever.

// The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
// to the last. You must plan your artifact retrieval carefully to maximize the 
// number of artifacts collected in a single night without triggering the curse.

// Given an integer array  artifacts  representing the number of artifacts in each 
// tomb, return the   maximum   number of artifacts you can collect without 
// disturbing any two adjacent tombs on the same night.

// Example 1:  
// Input:
// 2 4 3
// Output:  
// 4   

// Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
// as they are adjacent in a circular setup.


// Example 2:  
// Input:
// 1 2 3 1
// Output:  
// 4

// Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
// breaking the ancient rule.  
// Total = 1 + 3 = 4 artifacts.


// Example 3:  
// Input:
// 1 2 3
// Output:  
// 3 

// Constraints:  
// -  1 <= artifacts.length <= 100 
// -  0 <= artifacts[i] <= 1000 


import java.util.*;

public class TombsRob{
    public static int check(int[] artifacts, int i, int j, int[] dp){
        if(i > j){
            return 0;
        }
        if(i==j){
            return artifacts[i];
        }
        if(dp[i] != 0){
            return dp[i];
        }
        return dp[i] = Math.max(artifacts[i] + check(artifacts,i+2,j,dp), check(artifacts,i+1,j,dp));
    }
    public static int getTombs(int[] artifacts){
        if(artifacts.length == 1){
            return artifacts[0];
        }
        else if(artifacts.length == 2){
            return Math.max(artifacts[0],artifacts[1]);
        }
        
        int first = check(artifacts,1,artifacts.length-1,new int[artifacts.length]);
        int last = check(artifacts,0,artifacts.length-2,new int[artifacts.length-1]);
        
        return Math.max(first,last);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] artifacts = new int[input.length];
        for(int i = 0;i<input.length;i++){
            artifacts[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getTombs(artifacts));
        sc.close();
    }
}