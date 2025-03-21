// In networking, a subnet is a portion of a network with a defined range of IP addresses. 
// Two subnets overlap if they share some common IP addresses. Given two network 
// IP addresses with their respective CIDR notations, write a program that determines 
// whether these subnets overlap.

// Your program should take as input:

// IP1: The first subnet’s network address.
// CIDR1: The CIDR notation (prefix length) for the first subnet.
// IP2: The second subnet’s network address.
// CIDR2: The CIDR notation (prefix length) for the second subnet.
// The program should return true if the subnets overlap and false otherwise.

// Input Format:
// -------------
// A string IP1: The first network IP address (e.g., "192.168.1.0").
// An integer CIDR1: The subnet mask prefix (e.g., 24 for /24).
// A string IP2: The second network IP address (e.g., "192.168.1.128").
// An integer CIDR2: The subnet mask prefix for the second subnet.

// Output Format:
// --------------
// A boolean value, if the two subnets overlap or not.


// Sample Input:
// -------------
// 192.168.1.0
// 24
// 192.168.1.128
// 25

// Sample Output:
// --------------
// true

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0
// 192.168.1.64
// 192.168.1.128
// 192.168.1.192


// Sample Input:
// -------------
// 10.0.0.0
// 16
// 10.1.0.0
// 16

// Sample Output:
// --------------
// false

import java.util.*;
public class Subnet5{
    public static int ipToInt(String ip){
        String[] parts = ip.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);
        return (a<<24) | (b<<16) | (c<<8) | (d);
    }
    public static int calcSubnetMask(int n){
        return n == 0 ? 0 : ~((1<<(32-n))-1);
    }
    public static boolean isOverlapping(String ip1, int cidr1, String ip2, int cidr2){
        int firstIp = ipToInt(ip1);
        int secondIp = ipToInt(ip2);
        int firstMask = calcSubnetMask(cidr1);
        int secondMask = calcSubnetMask(cidr2);
        
        int firstNetwork = firstIp & firstMask;
        int secondNetwork = secondIp & secondMask;
        
        int firstBroadcast = firstNetwork | ~firstMask;
        int secondBroadcast = secondNetwork | ~secondMask;
        
        if(firstNetwork<=secondNetwork){
            return firstBroadcast>=secondNetwork;
        }
        return secondBroadcast>=firstNetwork;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String ip1 = sc.next();
        int cidr1 = sc.nextInt();
        String ip2 = sc.next();
        int cidr2 = sc.nextInt();
        System.out.println(isOverlapping(ip1,cidr1,ip2,cidr2));
        sc.close();
    }
}