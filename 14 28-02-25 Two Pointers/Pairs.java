// Two brothers want to play a game, 
// The rules of the game are: one player gives two sorted lists of 
// numerical elements and a number (sum). 
// The opponent has to find the closest pair of elements 
// to the given sum.
// -> pair consists of elements from each list

// Please help those brothers to develop a program, that takes 
// two sorted lists as input and return a pair as output.

// Input Format:
// -------------
// size of list_1
// list_1 values
// size of list_2
// list_2 values
// closest number

// Output Format:
// --------------
// comma-separated pair

// Sample Input-1:
// ---------------
// 4
// 1 4 5 7
// 4
// 10 20 30 40
// 32
// Sample Output-1
// ---------------
// 1, 30

// Sample Input-2
// ---------------
// 3
// 2 4 6
// 4
// 5 7 11 13
// 15
// sample output-2
// ---------------
// 2, 13

import java.util.*;

public class Pairs{
    public static ArrayList<Integer> getClosest(int[] arr1, int[] arr2, int n1, int n2, int closest){
        ArrayList<Integer> res = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        int left = 0;
        int right = n2-1;
        int minIndex = left;
        int maxIndex = right;
        while(left<n1 && right>=0){
            int current = arr1[left] + arr2[right];
            int diff = Math.abs(current-closest);
            if(diff<minDiff){
                minDiff = diff;
                minIndex = left;
                maxIndex = right;
            }
            if(current > closest){
                right--;
            }
            else{
                left++;
            }
        }
        res.add(arr1[minIndex]);
        res.add(arr2[maxIndex]);
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];
        for(int i = 0;i<n1;i++){
            arr1[i] = sc.nextInt(); 
        }
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        for(int i = 0;i<n2;i++){
            arr2[i] = sc.nextInt();
        }
        int closest = sc.nextInt();
        System.out.println(getClosest(arr1,arr2,n1,n2,closest));
        sc.close();
    }
}