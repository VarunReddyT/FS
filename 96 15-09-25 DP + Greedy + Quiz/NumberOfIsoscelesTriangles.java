// An Isosceles triangle is a triangle having two sides with equal in length.
// for example: three points i, j, k are given, if the distance between i and j, 
// i and k are equal. So that, i, j, k can form isosceles triangle.

// You are given n points in a flat surface, the point consist of x and y co-ordinates.
// Your task is to findout the number of isosceles triangles can be formed.

// Input Format:
// -------------
// Line-1 : An integer N.
// Next N lines : Two space separated integers, x & y coordinates.

// Output Format:
// --------------
// Print an integers, number of Isosceles triangles


// Sample Input-1:
// ---------------
// 3
// 0 0
// 1 0
// 2 0

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// The two isosceles triangles are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

import java.util.*;

public class NumberOfIsoscelesTriangles{
    public static int getIsoscelesTriangles(int[][] arr, int n){
        Map<Double,Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(i != j){
                    int distX = arr[i][0] - arr[j][0];
                    int distY = arr[i][1] - arr[j][1];
                    double dist = Math.pow(distX,2) + Math.pow(distY,2);
                    map.put(dist, map.getOrDefault(dist,0)+1);
                }
            }
            for(Map.Entry<Double,Integer> entry : map.entrySet()){
                if(entry.getValue() > 1){
                    res += entry.getValue() * (entry.getValue()-1);
                }
            }
            map.clear();
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i = 0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        System.out.println(getIsoscelesTriangles(arr,n));
        sc.close();
    }
}