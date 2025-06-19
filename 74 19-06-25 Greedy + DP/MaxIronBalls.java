// Bablu is playing with Magnets and Iron balls.
// Bablu has given a flat surface of m*n size, 
// each position contains either empty '0', an Iron ball 'B' 
// or Wooden Block 'W' (The wooden block is an anti-magnetic agent), 

// Your task is to help Bablu to find the maximum number of 
// Iron Balls he can attract by using a Magnet.

// The Magnet attracts all the iron balls in the same row and column 
// from their positions until the wooden block.
// since the wooden block is an anti-magnetic block.

// Note: You can only put the magnet in an empty position.

// Input Format:
// -------------
// Line-1: Two integers m and n, size of the surface.
// Next 'm' lines:  'n' space-separated characters only ('0', 'B', 'W').

// Output Format
// --------------
// Print an integer, the maximum number of Iron Balls can be attracted by using a Magnet


// Sample Input-1:
// ----------------
// 3 4
// 0 B 0 0 
// B 0 W B
// 0 B 0 0

// Sample Output:
// --------------
// 3 

// Explanation:
// ------------
// For the given grid,
// 	0 B 0 0 
// 	B 0 W B
// 	0 B 0 0
// Placing a Magnet at (1,1) attacts 3 iron balls. 


import java.util.*;

public class MaxIronBalls{
    public static int getIronBalls(char[][] arr, int m, int n){
        int count = 0;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                int temp = 0;
                if(arr[i][j] == '0'){
                    for(int k = i-1;k>=0;k--){
                        if(arr[k][j] == 'W'){
                            break;
                        }
                        if(arr[k][j] == 'B'){
                            temp++;
                        }
                    }
                    for(int k = i+1;k<m;k++){
                        if(arr[k][j] == 'W'){
                            break;
                        }
                        if(arr[k][j] == 'B'){
                            temp++;
                        }
                    }
                    for(int k = j-1;k>=0;k--){
                        if(arr[i][k] == 'W'){
                            break;
                        }
                        if(arr[i][k] == 'B'){
                            temp++;
                        }
                    }
                    for(int k = j+1;k<n;k++){
                        if(arr[i][k] == 'W'){
                            break;
                        }
                        if(arr[i][k] == 'B'){
                            temp++;
                        }
                    }
                }
                count = Math.max(count,temp);
            }
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] arr = new char[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = sc.next().charAt(0);
            }
        }
        System.out.println(getIronBalls(arr,m,n));
        sc.close();
    }
}