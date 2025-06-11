// Pramod is planning to design a program, which helps to create 
// the IP addresses posssible from a given string S, 
// where each IP address should be valid.

// It is guaranteed that S contains only digits.

// Can you help pramod in designing such program, which returns all possible 
// IP addresses. Print the answer in lexicographic order.

// NOTE:
// -----

// - A valid IP address consists of exactly four integers, 
// each integer is between 0 and 255, separated by single dots 
// and cannot have leading zeros
// - IP Addresses are said to be valid if it falls in the range 
// from 0.0.0.0 to 255.255.255.255

// - IP addresses like [123.012.234.255 , 123.234.345.34] are invalid.

// Input Format:
// -------------
// A string S, contains only digits [0-9].

// Output Format:
// --------------
// Print all possible IP addresses which are valid.


// Sample Input-1:
// ---------------
// 23323311123

// Sample Output-1:
// ----------------
// [233.233.11.123, 233.233.111.23]


// Sample Input-2:
// ---------------
// 12345678

// Sample Output-2:
// ----------------
// [1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]


// Sample Input-3:
// ---------------
// 02550255

// Sample Output-3:
// ----------------
// [0.25.50.255, 0.255.0.255]

import java.util.*;

public class ValidIPAddresses{
    public static boolean isValid(String i){
        if(i.length() > 3){
            return false;
        }
        if(i.charAt(0) == '0' && i.length()>1){
            return false;
        }
        if(Integer.parseInt(i) < 0 || Integer.parseInt(i)>255){
            return false;
        }
        return true;
        
    }
    public static void backtrack(List<String> res, String s, int count, String curr, int idx){
        if(count == 4 && idx == s.length()){
            res.add(curr);
            return;
        }
        if(count >= 4){
            return;
        }
        for(int i = idx; i<idx+3 && i<s.length();i++){
            String part = s.substring(idx,i+1);
            if(isValid(part)){
                if(count == 3){
                    backtrack(res,s,count+1,curr+part,i+1);
                }
                else{
                    backtrack(res,s,count+1,curr+part+".",i+1);
                }
            }
        }
        
    }
    public static List<String> getIPAddresses(String s){
        List<String> res = new ArrayList<>();
        backtrack(res,s,0,"",0);
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getIPAddresses(s));
        sc.close();
    }
}