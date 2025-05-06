// You are given a crystal with an energy level n. Your goal is to discover all the 
// different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order doesn't matter.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []



// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.

import java.util.*;

public class UniqueCombinations{
    public static void backtrack(List<List<Integer>> res, List<Integer> temp, int n, int start){
        if(n == 1){
            if(temp.size() > 1){
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for(int i = start;i<=n;i++){
            if(n%i == 0){
                temp.add(i);
                backtrack(res,temp,n/i,i);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static List<List<Integer>> getUniqueCombinations(int n){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(res, temp, n, 2);
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getUniqueCombinations(n));
        sc.close();
    }
}