// Somesh is given a task to you on Strings.
// You have given a string S ([a-z]), and an integer array Arr[]
// Now your task is to modify 'S' in such way:
// replace the 'i+1' characters in the string, with next i-th character 
// in alphabetic order(cyclic).

// For example, if S="art", Arr[]=[2,3,5] is 3, 
// i=0, modify('a') = 'c' , S="crt".
// i=1, modify('c') = 'f', modify('r') = 'u', S="fut".
// i=2, modify('f') = 'k', modify('u') = 'z', modify('t') = 'y', S="kzy"
// Finally modified string is kzy. 

// Note: if arr[i]=3 modify('z') ='c'

// Return the final modified string after all such modifications to S are applied.

// Input Format:
// -------------
// Line-1 -> A String S, length of S is L
// Line-2 -> L space separated integers.

// Output Format:
// --------------
// Print modifed String.


// Sample Input-1:
// ---------------
// adbp
// 1 2 3 4

// Sample Output-1:
// ----------------
// kmit

import java.util.*;

public class ModifyString{
    public static String getModifyString(String s, int[] arr){
        int prefSum = 0;
        for(int i : arr){
            prefSum += i;
        }
        char[] res = s.toCharArray();
        for(int i = 0;i<arr.length;i++){
            res[i] = (char)(res[i] + (prefSum%26));
            if(res[i]-'a' > 25){
                res[i] = (char)(res[i] - 26);
            }
            prefSum -= arr[i];
        }
        String modify = "";
        for(char c : res){
            modify += c;
        }
        return modify;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] parts = sc.nextLine().split(" ");
        int[] arr = new int[parts.length];
        for(int i = 0;i<parts.length;i++){
            arr[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(getModifyString(s,arr));
        sc.close();
    }
}