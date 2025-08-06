// The laser show at the Lumbini Park is something not to be missed.
// But, as per govt rule they have to follow COVID-19 restrictions.
// The management planning to guide the audience to sit in the seats
// that maximizes the distance to the closest person, in order to
// practice the social distance in the auditorium.

// Please help usher to guide the audience to sit in a seat by following few rules:

// - There are N seats in a single row, seats are numbered from 0 to N-1.
// - Maximize the distance from person to the closest person.
// - If there are multiple seats with similar distance, they sit in the seat with the lowest number.
// - First person always sit in seat number 0.
// - If a person leaves the auditorium from a seat, please consider that the seat is vacant

// Create a class Auditorium and two functions in it.
// 1. int seat(): represent the seat number of audience to sit.
// 2. void leave(int s): person leaves the auditorium from a seat number 's'.

// Input Format:
// ----------------
// Line-1 -> two integers N and P, Number of seats N, Number of Operations P
// P lines of input -> each line contains funtion number and parameter list (if required).

// Output Format:
// ------------------
// Print the alloted seat numbers in one line as output.


// Sample Input-1:
// -------------------
// 10 6
// 1
// 1
// 1
// 1
// 2 4
// 1


// Sample Output-1:
// ---------------------
// 0 9 4 2 5

// NOTE:
// -----
// In the sample input:
//     - option 1 indicates, calling "int seat()" method.
//     - option 2 indicates, calling "void leave(seat_num)" method.
    

import java.util.*;

class Auditorium{
    int n;
    int[] arr;
    int low, high;
    Auditorium(int n){
        this.n = n;
        this.arr = new int[n];
        this.low = -1;
        this.high = -1;
    }
    public int seat(){
        if(low == -1){
            low = 0;
            arr[0] = 1;
            return 0;
        }
        else if(high == -1){
            high = n-1;
            arr[n-1] = 1;
            return n-1;
        }
        int max = 0;
        int seat = 0;
        int prev = -1;
        for(int i = 0;i<n;i++){
            if(arr[i] == 1){
                if(prev == -1){
                    if(i > max){
                        seat = 0;
                        max = i;
                    }
                }
                else{
                    int dist = (i - prev)/2;
                    if(dist > max){
                        max = dist;
                        seat = prev + dist;
                        
                    }
                }
                prev = i;
            }
        }
        if(arr[n-1] == 0 && n-1-prev > max){
            seat = n-1;
        }
        arr[seat] = 1;
        low = Math.min(low,seat);
        high = Math.max(high,seat);
        return seat;
    }
    public void leave(int seat_num){
        arr[seat_num] = 0;
    }
}

public class SeatPlacement{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        Auditorium audi = new Auditorium(n);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0;i<p;i++){
            int op = sc.nextInt();
            if(op == 1){
                res.add(audi.seat());
            }
            else{
                audi.leave(sc.nextInt());
            }
        }
        System.out.println(res);
        sc.close();
    }
}