// Bablu is working in a construction field.
// He has N number of building blocks, where the height and width of all the blocks are same.
// And the length of each block is given in an array, blocks[].

// Bablu is planned to build a wall in the form of a square.
// The rules to cunstruct the wall are as follows:
// 	- He should use all the building blocks.
// 	- He should not break any building block, but you can attach them with other.
// 	- Each building-block must be used only once.
	
// Your task is to check whether Bablu can cunstruct the wall as a square
// with the given rules or not. If possible, print true. Otherwise, print false.

// Input Format:
// -------------
// Line-1: An integer N, number of BuildingBlocks.
// Line-2: N space separated integers, length of each block.

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 6
// 1 2 6 4 5 6

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 6
// 5 3 2 5 5 6

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class ConstructSquare{
    public static boolean backtrack(int[] arr, int side, boolean[] visited, int currSum, int[] count){
        if(count[0] == 4){
            for(boolean x : visited){
                if(!x){
                    return false;
                }
            }
            return true;
        }
        if(currSum == side){
            return true;
        }
        if(currSum > side){
            return false;
        }
        for(int i = 0;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                if(backtrack(arr,side,visited,currSum + arr[i],count)){
                    count[0]++;
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
    public static boolean checkIfSquare(int[] arr, int n, int sum){
        if(sum%4 != 0 || arr.length < 4){
            return false;
        }
        int side = sum/4;
        boolean[] visited = new boolean[n];
        return backtrack(arr,side,visited,0,new int[]{0});
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        System.out.println(checkIfSquare(arr,n,sum));
        sc.close();
    }
}
// int count = 0;
// for(int i = 0;i<n;i++){
    //     if(backtrack(arr,side,visited,0,i,count)){
//         count++;
//     }
//     if(count == 4){
    //         return true;
    //     }
    // }
    // public static boolean backtrack(int[] arr, int side, boolean[] visited, int currSum, int index, int count){
    //     if(currSum == side){
    //         return true;
    //     }
    //     if(currSum > side || index > arr.length-1){
    //         return false;
    //     }
    //     for(int i = 0;i<arr.length;i++){
    //         if(!visited[i]){
    //             visited[i] = true;
    //             if(backtrack(arr,side,visited,currSum + arr[i],i,count)){
    //                 return true;
    //             }
    //             visited[i] = false;
    //         }
    //     }
    //     return false;
    // }