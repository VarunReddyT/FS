// Mounika is creating the binary strings using P 1's and Q 0's.
// A binary string contains only 0's and 1's.
// She has given a list of binary strings binStr[] to be formed.

// Her task is to find, the maximum number of binary strings can be formed, 
// with given P 1's and Q 0's only. She cannot use any more 1's or 0's.

// Input Format:
// -------------
// Line-1 -> A single line of space separated binary strings, binStr[].
// Line-2 -> Two integers P and Q, P number of 1's and Q number of 0's


// Output Format:
// --------------
// Print an integer as output, maximum number of binary strings can be formed.


// Sample Input-1:
// ---------------
// 10 0001 111001 1 0
// 3 5

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// She can form 4 strings by the using of 3 1's and 5 0's
// strings are 10, 0001, 1, 0.


// Sample Input-2:
// ---------------
// 10 1 0
// 1 1

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// She can form 2 strings by the using of 1 1's and 1 0's
// strings are 1, 0.


import java.util.*;

public class MaxBinaryStrings {
    public static int noOfStrings(String[] inp, int p, int q){
        int[] res = new int[]{0};
        solve(res,p,q,inp,0,0);
        return res[0];
    }
    public static void solve(int[] res, int p, int q, String[] inp, int idx, int curr){
        if(idx == inp.length){
            res[0] = Math.max(res[0],curr);
            return;
        }
        if(p < 0 || q < 0){
            return;
        }
        int ones = 0;
        for(int i = 0;i<inp[idx].length();i++){
            if(inp[idx].charAt(i) == '1'){
                ones++;
            }
        }
        solve(res,p-ones,q-(inp[idx].length() - ones), inp, idx+1, curr+1);
        solve(res,p, q, inp, idx+1, curr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        int p = sc.nextInt();
        int q = sc.nextInt();
        System.out.println(noOfStrings(inp,p,q));
        sc.close();
    }
}
