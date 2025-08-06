// Pramod is working on Strings consist of digits only. He wants to findout, 
// whether the given string can form Fibonacci sequence or not.

// A String can form a Fibonacci Sequence, if it contains at least 
// three numbers, and numbers are in the following order:
// first, second, third  = first + second, fourth = third + second, .. so on.

// Return true, if the given string can form fibonacci sequence,
// otherwise, return false.

// Note: Numbers in the fibonacci sequence contains no leading 0's.
// for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

// Input Format:
// -------------
// A String consist of digits only

// Output Format:
// --------------
// Print a boolean value as result.


// Sample Input-1:
// ---------------
// 23581321

// Sample Output-1:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
// 2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

// Sample Input-2:
// ---------------
// 199100199

// Sample Output-2:
// ----------------
// true

// Explanation: 
// ------------
// Fibonacci Sequence is : 1 99 100 199
// 1, 99, 1+99=100, 99+100=199.

// Not working code

import java.util.*;

public class FibonacciSequence{
    public static boolean isFibonacci(String s){
        int n = s.length();
        for(int i = 0;i<n/2;i++){
            Long first = Long.parseLong(s.substring(0,i+1));
            if(s.charAt(i) == '0'){
                continue;
            }
            for(int j = i+1;j<n;j++){
                if(s.charAt(i+1) == '0' && j>i+1){
                    break;
                }
                Long second = Long.parseLong(s.substring(i+1,j+1));
                StringBuilder sb = new StringBuilder();
                sb.append(first).append(second);
                while(sb.length() < n){
                    Long third = first + second;
                    // System.out.println(first + " " + second + " " + third);
                    sb.append(third);
                    first = second;
                    second = third;
                    if(sb.toString().equals(s)){
                        return true;
                    }
                    // first = Long.parseLong(s.substring(0,i+1));
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(isFibonacci(s));
        sc.close();
    }
}