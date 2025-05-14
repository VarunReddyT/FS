// You are given two strings 'Org' and 'Sub' where Sub is a subsequence of Org. 
// You aer given a list of R indices[] (Unique indices), and you need to delete 
// P letters from the given string 'Org', with the following conditions:
//     - You need to delete the first P letters from strin 'Org'in the given order
//       of indices[] only.
//     - After deleting P letters, the string 'Sub' should be subsequence of 'Org'.
//       Where, 0 <= i < P and P < R.
     
// Your task is to maximixe the value of P such that 'Sub' is still a subsequence 
// of 'Org' after the deletion of letters, and return P.

// Input Format:
// -------------
// Line-1: Two space seperated strings, Original and Sub
// Line-2: An integer, R, number of indices.
// Line-3: R space separated integers, indices[].

// Output Format:
// --------------
// Print an integer, the maximum value of P.


// Sample Input-1:
// ---------------
// pqrprq pr
// 3
// 3 1 0

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// After deleting 2 letters at indices 3 and 1, "pqrprq" becomes "prrq".
// "pq" is a subsequence of "prrq".
// If you delete 3 letters at indices 3, 1, and 0, "pqrprq" becomes "rrq", 
// and "pq" is not a subsequence of "rrq".
// Hence, the maximum P is 2.

// Sample Input-2:
// ---------------
// pqrqssss pqrs
// 6
// 3 2 1 4 5 6

// Sample Output-2:
// ----------------
// 1

// Explanation: 
// ------------
// After deleting 1 letter at index 3, "pqrqssss" becomes "pqrssss".
// "pqrs" is a subsequence of "pqrssss".


import java.util.*;

public class MaxSubsequenceDeletions{
    public static boolean isSubsequence(String a, String b){
        if(a.length()<b.length()){
            return false;
        }
        int i = 0;
        int j = 0;
        while(i<a.length() && j<b.length()){
            if(a.charAt(i) == b.charAt(j)){
                j++;
            }
            i++;
        }
        return j == b.length();
    }
    public static int getMaxDeletions(String org, String sub, int[] arr, int n){
        int count = 0;
        boolean[] deleted = new boolean[org.length()];
        for(int i = 0;i<n;i++){
            deleted[arr[i]] = true;
            // int index = arr[i]-count;
            // if(index < 0 || index >= sb.length()){
            //     break;
            // }
            // sb.deleteCharAt(index);
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<org.length();j++){
                if(!deleted[j]){
                    sb.append(org.charAt(j));
                }
            }
            if(isSubsequence(sb.toString(),sub)){
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n = sc.nextInt();
        int[] indices = new int[n];
        for(int i = 0;i<n;i++){
            indices[i] = sc.nextInt();
        }
        System.out.println(getMaxDeletions(input[0],input[1],indices,n));
        sc.close();
    }
}