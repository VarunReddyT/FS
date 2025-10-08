// Shaggy Rogers is working with numbers.
// He is given a number N, 
// He wants to replace all occurrences of a digit in the number N,
// with another digit between [0-9]. May be with same number too.

// But there should not be any leading zeros in the number after the replacement,
// Or the number should not become zero.

// Rogers can perform the replacement of the occurrences of the digit in N for two 
// times, He will generate two new numbers P and Q, such that the value of |P-Q| 
// should be Maximum.

// Your task is to help Shaggy Roers to find the maximum difference of P and Q possible.


// Input Format:
// -------------
// An integer N, the number

// Output Format:
// --------------
// Print an integer, the maximum difference of P and Q


// Sample Input-1:
// ---------------
// 123

// Sample Output-1:
// ----------------
// 820

// Explanation:
// ------------
// Replacement-1: replace 1 with 9 you will get P as 923
// Replacement-2: replace 2 with 0 you will get Q as 103
// So Max difference is 820.


// Sample Input-2:
// ---------------
// 4254

// Sample Output-2:
// ----------------
// 8008

// Explanation:
// ------------
// Replacement-1: replace 4 with 9 you will get P as 9259
// Replacement-2: replace 4 with 1 you will get Q as 1259
// So Max difference is 8008.


import java.util.*;

public class MaxDiff{
    public static int getMaxDiff(int n){
        StringBuilder sb = new StringBuilder(Integer.toString(n));
        char caseMax = '0';
        for(int i = 0;i<sb.length();i++){
            if(sb.charAt(i) != '9'){
                caseMax = sb.charAt(i);
                break;
            }
        }
        if(caseMax != '0'){
            for(int i = 0;i<sb.length();i++){
                if(sb.charAt(i) == caseMax){
                    sb.setCharAt(i,'9');
                }
            }
        }
        int maxNum = Integer.parseInt(sb.toString());
        sb = new StringBuilder(Integer.toString(n));
        char caseMin = '0';
        boolean isFirstLetter = false;
        if(sb.charAt(0) != '1'){
            caseMin = sb.charAt(0);
            isFirstLetter = true;
        }
        else{
            for(int i = 0;i<sb.length();i++){
                if(sb.charAt(i) != '1' && sb.charAt(i) != '0'){
                    caseMin = sb.charAt(i);
                    break;
                }
            }
        }
        if(caseMin != '0'){
            for(int i = 0;i<sb.length();i++){
                if(sb.charAt(i) == caseMin){
                    sb.setCharAt(i,isFirstLetter ? '1' : '0');
                }
            }
        }
        int minNum = Integer.parseInt(sb.toString());
        return maxNum - minNum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getMaxDiff(n));
        sc.close();
    }
}