// In computer networking, subnetting is the process of dividing a larger IP network
// into multiple smaller subnetworks (subnets). Given a base network IP address, 
// a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
// write a Java program that calculates the starting addresses of all the subnets.

// Your program should take as input:
// 	- A base network address (e.g., 192.168.1.0).
// 	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
// 	- The number of subnets to generate.

// The program should then compute and return the starting addresses of each subnet.

// Input Format:
// -------------
// A string NIP: The base network IP address (e.g., "192.168.1.0").
// An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
// An integer numSubnets: The number of subnets to be generated.

// Output Format:
// --------------
// A list of subnet addresses, each representing the starting address of a subnet.


// Sample Input:
// -------------
// 192.168.1.0
// 26
// 4

// Sample Output:
// --------------
// [192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0/28, 
// 192.168.1.16/28, 
// 192.168.1.32/28, 
// 192.168.1.48/28

import java.util.*;

public class Subnet4{
    public static int getNewPrefix(int cidr, int subnets){
        int n = 0;
        while(true){
            if((int)Math.pow(2,n) >= subnets){
                return cidr+n;
            }
            n++;
        }
    }
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
    public static String intToIp(int ip){
        return String.format("%d.%d.%d.%d",
            (ip >> 24) & 255,
            (ip >> 16) & 255,
            (ip >> 8) & 255,
            (ip) & 255
        );
    }
    public static ArrayList<String> getAddresses(String ip, int cidr, int subnets){
        ArrayList<String> arr = new ArrayList<>();
 
        int newPrefix = getNewPrefix(cidr,subnets);
        int newSubnetMask = calcSubnetMask(newPrefix);
        int intIp = ipToInt(ip);
        for(int i = 0;i<subnets;i++){
            int subnet = intIp & newSubnetMask | (i<<(32-newPrefix));
            arr.add(intToIp(subnet) + "/" + newPrefix);
        }
       
        return arr;
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String ip = sc.next();
        int cidr = sc.nextInt();
        int subnets = sc.nextInt();
        System.out.println(getAddresses(ip,cidr,subnets));
        sc.close();
    }
}