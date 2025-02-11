
// Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
// whether IP-1 and IP-2 belongs to same host range or not.

// Input Format:
// ---------------
// Two space separated strings, IP1 and IP2.
// An integer, CIDR (subnet mask).
    
// Output Format:
// ---------------
// A boolean result.


// Sample Input-1:
// -----------------
// 192.168.1.10 192.168.1.20
// 24

// Sample Output-1:
// ------------------
// true


// Sample Input-2:
// -----------------
// 192.0.2.1 192.0.3.253
// 24

// Sample Output-2:
// ------------------
// false



import java.util.*;

public class Subnet3{
    public static int ipToInt(String ip){
        String[] parts = ip.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);
        return (a<<24) | (b<<16) | (c<<8) | d;
    }
    public static boolean check(String ip1, String ip2, int prefLen){
        int mask = 0xFFFFFFFF << (32-prefLen);
        int intIp1 = ipToInt(ip1);
        int intIp2 = ipToInt(ip2);
        int network1 = intIp1 & mask;
        int network2 = intIp2 & mask;
        if(network1 == network2){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String ip1 = sc.next();
        String ip2 = sc.next();
        int prefLen = sc.nextInt();
        System.out.println(check(ip1,ip2,prefLen));
        sc.close();
    }
}