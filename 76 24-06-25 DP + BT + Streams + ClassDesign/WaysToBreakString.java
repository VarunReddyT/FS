// Bunny is playing with binary strings. He wants to break
// a binary string B into 3 parts, of length atleast '1',
// when we merge the 3 parts will result the string B.

// Your task is to find the count the various forms to break 
// the Binary String B into 3 parts, where each part should 
// contain equal number of 1's.


// Input Format:
// -------------
// A string S.

// Output Format:
// --------------
// Print an integer, count the various forms to break.


// Sample Input-1:
// ---------------
// 01010010

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// There are six forms to break S into 3 parts 
// where each part contain the equal number of  1's.
// 01 | 01 | 0010
// 01 | 010 | 010
// 01 | 0100 | 10
// 010 | 1 | 0010
// 010 | 10 | 010
// 010 | 100 | 10


// Sample Input-2:
// ---------------
// 010010

// Sample Output-2:
// ----------------
// 0

import java.util.*;

public class WaysToBreakString{
    public static void backtrack(int[] res, String s, int target, int index, int curr){
        if(index == s.length()){
            if(curr == 3){
                res[0]++;
            }
            return;
        }
        if(curr > 3){
            return;
        }
        int oneCount = 0;
        for(int i = index;i<s.length();i++){
            if(s.charAt(i) == '1'){
                oneCount++;
            }
            if(oneCount > target){
                break;
            }
            if(oneCount == target){
                backtrack(res,s,target,i+1,curr+1);
            }
        }
    }
    public static int getBreakWays(String s){
        int count = 0;
        for(char i : s.toCharArray()){
            if(i == '1'){
                count++;
            }
        }
        if(count == 0){
            return (((s.length()-1)*(s.length()-2))/2);
        }
        if(count % 3 != 0){
            return 0;
        }
        int[] res = {0};
        backtrack(res,s,count/3,0,0);
        return res[0];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(getBreakWays(s));
        sc.close();
    }
}