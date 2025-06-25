// Basava is interested in playing with digits.
// He wants to create a set of integers of length N, using the digits[0-9].
// The rules to create the integers are as follows:
// 	- digits in each integer are like d0,d1,d2...dn-1
// 	- and |d0-d1| = |d1-d2| = |d2-d3| ... |dn-2 - dn-1|= D

// Basava is given two integers N and D, where N is length of the integer and 
// D is the difference. Can you help Basava, to create such list of integers 
// and print all the possible integers in ascending order


// Note:
// -----
// Integers with leading 0's are not allowed


// Input Format:
// -------------
// Two space separated integers N and K.

// Output Format:
// --------------
// Print all the possible integers in ascending order.


// Sample Input-1:
// ---------------
// 3 5

// Sample Output-1:
// ----------------
// [161, 272, 383, 494, 505, 616, 727, 838, 949]


// Sample Input-2:
// ---------------
// 2 3

// Sample Output-2:
// ----------------
// [14, 25, 30, 36, 41, 47, 52, 58, 63, 69, 74, 85, 96]

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GenerateNumbers{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        List<String> res = new ArrayList<>();
        bt(res,new StringBuilder(),n,d,0);
        System.out.println(res);
        sc.close();
    }
    public static void bt(List<String> res,StringBuilder sb,int n,int d,int curr){
        if(curr==n){
            res.add(sb.toString());
            return;
        }
        for(int i=0;i<10;i++){
            if(curr==0){
                if(i==0) continue;
                sb.append(i+"");
                bt(res,sb,n,d,curr+1);
                sb.deleteCharAt(sb.length()-1);
            }
            else{
                if(Math.abs(Integer.parseInt(sb.charAt(curr-1)+"")-i)==d){
                    sb.append(i+"");
                    bt(res,sb,n,d,curr+1);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}