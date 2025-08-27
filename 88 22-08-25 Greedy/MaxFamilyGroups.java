// The laser show at Lumbini park started online bookings,
// It is an open theater having n rows of seats, each row consist of 10 seats.
// The seats are labelled from 1 to 10 numbers, as shown below.

// 	    Row	1 2 3	 4 5 6 7 	8 9 10
// 		1	_ _ _	 _ _ _ _	_ _ _
// 		2	_ _ _	 _ _ _ _	_ _ _
// 		......
// 		n	_ _ _	 _ _ _ _	_ _ _

// You will be given n value, and a list of booked seats booked[],
// i.e, booked[x]= [i,j] indicates, i-th row j-th labelled seat is booked.

// If a family-group of four members have to book the seats,
// with the conditions are as follows:
// 	- Book four adjacent seats in a single row.
// 	- Seats across walkway (3 and 4) or (7 and 8) are not considered to be adjacent, 
// 	- There is an exceptional case on which the walkway split a four members group, 
// 	   in to two people on each side of walkway, like [2,3 4,5] or [6,7 8,9].
	   
// Find out, the maximum number of family groups can book the seats,

	   
// Input Format:
// -------------
// Line-1: Two integers n and b, number of rows, and number of bookings.
// next b lines: two integers i, j, row number and seat number.

// Output Format:
// --------------
// Print an intger, the number of ways.


// Sample Input:
// ---------------
// 3 6
// 1 2
// 1 3
// 1 8
// 2 6
// 3 1
// 3 10

// Sample Output:
// ----------------
// 4

// Explanation:
// ------------
// Row     1 2 3	4 5 6 7 	8 9 10
// 1       _ x x	b b b b		x _ _
// 2       _ b b	b b x _		_ _ _
// 3       x b b	b b b b		b b x
		
// In row-1, we can fit for 1 family-group
// In row-2, we can fit for 1 family-group
// In row-3, we can fit for 2 family-groups

import java.util.*;

public class MaxFamilyGroups{
    public static boolean isFree(boolean[] row, int start, int end){
        for(int i = start;i<=end;i++){
            if(row[i]){
                return false;
            }
        }
        return true;
    }
    public static int getFamilyRowCount(boolean[] row){
        int count = 0;
        
        // Possible cases
        // 2 3 4 5
        // 4 5 6 7
        // 6 7 8 9

        boolean case1 = isFree(row,1,4);
        boolean case2 = isFree(row,3,6);
        boolean case3 = isFree(row,5,8);

        if(case1) count++;
        if(case3) count++;
        if(!case1 && !case3 && case2) count++;

        return count;
    }
    public static int maxFamilyGroups(boolean[][] booked){
        int res = 0;
        for(boolean[] i : booked){
            res += getFamilyRowCount(i);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        boolean[][] booked = new boolean[n][10];
        for (int i = 0; i < b; i++) {
            int row = sc.nextInt() - 1;
            int seat = sc.nextInt() - 1;
            booked[row][seat] = true;
        }
        System.out.println(maxFamilyGroups(booked));
        sc.close();
    }
}