// There are floods in the eastern India.There are infinite number ofboats available
// with National Disaster Response force.Where each boat can carry a maximum weight 
// limit.

// Each boat carries at most two people at same time provided the sum of those people 
// is at most limit. 

// Return the minimum number of boats to carry every given person to rescue them
 
// Input Format
// ------------
// Line1: Two space separated integers, representing no of people and limit of boat
// Line2: space separated integers represents weight of each person 

// Output Format
// -------------
// An integer represents minimum no of boats required


// Example 1:
// -----------
// Input1: 2 3
//         1 2
// Output: 1
// Explanation: 1 boat (1, 2)


// Example 2:
// ----------
// Input2: 4 3
//         3 2 2 1
// Output2: 3
// Explanation: 3 boats (1, 2), (2) and (3)


// Example 3:
// ----------
// Input3: 4 5
//         3 5 3 4
// Output3: 4
// Explanation: 4 boats (3), (3), (4), (5)


import java.util.*;

public class MinBoats{
    public static int getBoats(int[] arr, int n, int max){
        Arrays.sort(arr);
        int i = 0;
        int j = n-1;
        int count = 0;
        while(i<=j){
            if(arr[i]+arr[j] <= max){
                i++;
            }
            j--;
            count++;
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(getBoats(arr,n,max));
        sc.close();
    }
}