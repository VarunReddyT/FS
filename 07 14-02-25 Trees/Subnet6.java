// Write a Java program that determines the class of a given IPv4 address. 
// The classification follows the standard IP address classes:
// 	- Class A: IP addresses where the first octet is in the range 1-127.
// 	- Class B: IP addresses where the first octet is in the range 128-191.
// 	- Class C: IP addresses where the first octet is in the range 192-223.
// 	- Multicast (Class D): IP addresses where the first octet is in the range 224-239.

// Input Format:
// -------------
// A string IP: The first network IP address (e.g.,0-239).

// Output Format:
// --------------
// A boolean value, if the two subnets overlap or not.


// Sample Input:
// -------------
// 192.168.1.0

// Sample Output:
// --------------
// Class C

// Explanation:
// ------------
// The first octet 192 is within the Multicast range.

import java.util.*;

public class Subnet6{
    public static String getClass(int n){
        if(n<=127){
            return "Class A";
        }
        else if(n<=191){
            return "Class B";
        }
        else if(n<=223){
            return "Class C";
        }
        else{
            return "Multicast";
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.next().split("\\.");
        System.out.println(getClass(Integer.parseInt(s[0])));
        sc.close();
    }
}