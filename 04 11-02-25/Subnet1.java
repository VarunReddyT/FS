/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/

import java.util.*;

public class Subnet1{
    public static String getIp(int n){
        int mask = n == 0 ? 0 : ~((1<<(32-n))-1);
        
        return String.format("%d.%d.%d.%d",
            (mask >> 24) & 0xFF,
            (mask >> 16) & 0xFF,
            (mask >> 8) & 0xFF,
            mask & 0xFF
        );

        // StringBuilder sb = new StringBuilder();
        
        // sb.append((mask>>24)&255);
        // sb.append('.');
        // sb.append((mask>>16)&255);
        // sb.append('.');
        // sb.append((mask>>8)&255);
        // sb.append('.');
        // sb.append((mask)&255);
        // return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getIp(n));
        sc.close();
    }
}