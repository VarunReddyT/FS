// In the near future, human engineers have realized that the optimal model for 
// organizing infrastructure in cities is the fully connected graph. 
// Kura recently moved into the town of Koenigsberg. Its a small town that consists 
// of only 4 houses, for each of its inhabitants.

// As the new arrival, Kura discovers that he has three neighbors: Leon, Lieb and Hamil. 
// Also, he finds a map of the town in your house. It looks like this in 
// a weighted graph format: Kura's house is marked as h, Leon's house is marked 
// as a, Lieb's house is marked as b, and Hamil's house is marked as c and 
// ha, hb, hc, ab, bc & ca represent the repective distances between the houses. 

// From the map, Kura was able to determine that the distance from any place ' x '
// on the map to a place ' y ' on the map is same as the distance from ' y ' to ' x '. 
// After spending the day unpacking, Kura decided to visit 2 of his neighbors. 
// Since, he has limited energy to walk, you must help Kura determine 
// the minimum distance he must walk to meet any 2 of his neighbors and come back home. 
// He doesn't mind visiting the same neighbor or passing the same road multiple times. 
// The only goal is to minimize the total distance traveled.


// Input Format:
// -------------
// space separated 6 integers representing ha, hb, hc, ac,ab,bc  

// Output Format:
// --------------
// A single number representing minimum distance that kura needs to travel to 
// visit any 2 of his neighbors and return home. 

// Constraints:
// ------------
// 1<=ha,hb,hc,ac,ab,bc<= 10^12

// Sample input:
// -------------
// 3 2 8 8 1 4

// Sample output: 
// --------------
// 6

import java.util.*;

public class TotalMinDistance{
    public static int getMinDistance(int ha, int hb, int hc, int ac, int ab, int bc){
        int min = Integer.MAX_VALUE;
        // ha, hb, hc, ac,ab,bc  
        min = Math.min(min,ha+ab+hb);
        min = Math.min(min,ha+ac+hc);
        min = Math.min(min,hb+ab+ha);
        min = Math.min(min,hb+bc+hc);
        min = Math.min(min,hc+ac+ha);
        min = Math.min(min,hc+bc+hb);
        
        return min;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] arr = new int[6];
        for(int i = 0;i<6;i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.println(getMinDistance(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]));
        sc.close();
    }
}