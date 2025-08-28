// A wall clock is a complete whole circle and cover 360°.
// You are given the time as HH:MM.
// Your task is to return the angle between the Hours hand and Minutes hand
// of the clock and the angle should not be reflex angle.

// Input Format:
// -------------
// A string time, HH:MM

// Output Format:
// --------------
// Print a double result, within 10^-5 of the original value.


// Sample Input-1:
// ---------------
// 04:30

// Sample Output-1:
// ----------------
// 45.00000


// Sample Input-2:
// ---------------
// 06:15

// Sample Output-2:
// ----------------
// 97.50000

import java.util.*;

public class AngleBetweenHandsInClock{
    public static double getAngle(int hours, int minutes){
        // boolean onSameSide = false;
        // if((hours>6 && minutes > 30) || (hours<6 && minutes < 30)){
        //     onSameSide = true;
        // }
        double hoursAngle = (hours*30) + (minutes*0.5);
        double minutesAngle = (minutes*6);
        
        // double hoursChange = minutes*0.5;
        
        // System.out.println(hoursAngle);
        // System.out.println(minutesAngle);
        // System.out.println(hoursChange);
        double res = Math.abs(hoursAngle-minutesAngle);
        return Math.min(res,360-res);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(":");
        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        System.out.println(getAngle(h,m));
        sc.close();
    }
}