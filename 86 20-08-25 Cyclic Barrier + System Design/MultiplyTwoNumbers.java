// Babylonians invented multiplication of numbers.

// You will be given two strings consist of digits only.
// You have to perform the multiplication of these two strings using 
// Babylonians approach, and return the result of multiplication of two strings.


// Input Format:
// -------------
// Two space separated strings, num1 and num2.

// Output Format:
// --------------
// Print the product of two strings num1 and num2.


// Sample Input-1:
// ---------------
// 5 4 

// Sample Output-1:
// ----------------
// 20


// Sample Input-2:
// ---------------
// 121 432 

// Sample Output-2:
// ----------------
// 52272

// Note:
// -----
// 	- Input should contain digits only, and should not contain leading 0's 
// 	  except number 0.
// 	- Output will be in the range of integer only.
// 	- There will be no leading minus '-' symbol also.
// 	- Should not use built-in BigInteger library.
// 	- Should not convert the given strings into integers.


import java.util.*;

public class MultiplyTwoNumbers{
    public static String multiply(String num1, String num2){
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int[] res = new int[num1.length() + num2.length() - 1];
        
        for(int i = 0;i<num1.length();i++){
            for(int j = 0;j<num2.length();j++){
                res[i+j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        
        for(int i = res.length-1;i>0;i--){
            res[i-1] += res[i]/10;
            res[i] %= 10;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : res){
            sb.append(i);
        }
        
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(multiply(input[0],input[1]));
        sc.close();
    }
}