// You are given a set of releases of a software and you are asked to find the most
// recent release. There may be multiple checkins of the software in a given branch. 
// Each branch may also have sub branches. For example release 3-5-4 refers to the 
// fourth checkin in the fifth sub branch of the third main branch. 
// This hierarchy can go upto any number of levels. 

// If a level is missing it is considered as level 0 in that hierarchy. 
// For example 3-5-7 is  same as 3-5-7-0 or even same as 3-5-7-0-0. 
// The higher numbers denote more recent releases. 

// For example 3-5-7-1 is more recent than 3-5-7 but less recent than 3-6.

// Input Format:
// -------------
// A single line space separated strings, list of releases 

// Output Format:
// --------------
// Print the latest release of the software.


// Sample Input-1:
// ---------------
// 1-2 1-2-3-0-0 1-2-3

// Sample Output-1:
// ----------------
// 1-2-3

// Sample Input-2:
// ---------------
// 3-5-4 3-5-7 3-5-7-1 3-5-7-0-0 3-6

// Sample Output-2:
// ----------------
// 3-6

import java.util.*;

public class MostRecentRelease{
    public static String getRecentRelease(String[] arr){
        Arrays.sort(arr);
        Set<String> set = new HashSet<>();
        for(String i : arr){
            set.add(i);
        }
        String res = arr[arr.length-1];
        int len = res.length()-1;
        StringBuilder sb = new StringBuilder(res);
        while(len >= 0){
            if(sb.charAt(len) == '0'){
                sb.deleteCharAt(len--);
                sb.deleteCharAt(len--);
            }
            else{
                break;
            }
        }
        if(set.contains(sb.toString())){
            return sb.toString();
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(getRecentRelease(input));
        sc.close();
    }
}