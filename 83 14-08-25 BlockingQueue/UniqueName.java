// Venkat wants to create a unique name for his child, to do that he is referring 
// two names, one is traditonal name(TN) and other is modern name(MN).

// He is planning to create unique name(UN), using the following ways:
//     - if traditional name TN is non empty, then add the first letter L of TN 
//     to unique name UN and remove the letter L from traditional name TN
// 	e.g., if TN = "havi" and UN="anvika", then after adding L to UN and remove L 
// 	from TN, the names updated as UN="hanvika", TN="avi".
   
//    - if modern name MN is non empty, then add the first letter L of MN 
//     to unique name UN and remove the letter L from modern name MN
//     e.g., if MN = "ram" and UN="ao", then after adding L to UN and remove L 
// 	from MN, the names updated as UN="rao", MN="am".
	
// You are given two names, TN and MN, 
// Your task is to help venkat to generate the unique name for his child,
// and the name should be largest in the dictionary order.

// For example, "kmit" is larer than "kmec", as third letter is greater in "kmit".

// Input Format:
// -------------
// Two space separated names, TN and MN.

// Output Format:
// --------------
// Print a string, the laregst unique name possible.


// Sample Input-1:
// ---------------
// sudha vivid

// Sample Output-1:
// ----------------
// vsuividhda

// Sample Input-2:
// ---------------
// appa banana

// Sample Output-2:
// ----------------
// bappananaa

import java.util.*;

public class UniqueName{
    public static String getUniqueName(String tn, String mn){
        int first = 0;
        int second = 0;
        StringBuilder sb = new StringBuilder();
        while(first < tn.length() && second < mn.length()){
            if(tn.charAt(first) > mn.charAt(second)){
                sb.append(tn.charAt(first++));
            }
            else if(tn.charAt(first) < mn.charAt(second)){
                sb.append(mn.charAt(second++));
            }
            else{
                if(tn.substring(first).compareTo(mn.substring(second)) >= 0){
                    sb.append(tn.charAt(first++));
                }
                else{
                    sb.append(mn.charAt(second++));
                }
            }
        }
        while(first < tn.length()){
                sb.append(tn.charAt(first++));
        }
        while(second < mn.length()){
                sb.append(mn.charAt(second++));
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(getUniqueName(input[0],input[1]));
        sc.close();
    }
}