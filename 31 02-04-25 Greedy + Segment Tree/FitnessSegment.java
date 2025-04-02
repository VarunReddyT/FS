// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6        //bestFitness
// 1 0 7        //bestFitness
// 2 2 18       //improveFitness
// 2 4 36       //improveFitness
// 1 2 7        //bestFitness


// Sample Output:  
// --------------
// 25
// 28
// 36


import java.util.*;

class SegmentTree{
    int[] t;
    int n;
    SegmentTree(int size){
        this.n = size;
        t = new int[4*n];
    }
    public void build(int[] arr, int v, int tl, int tr){
        if(tl==tr){
            t[v] = arr[tl];
        }
        else{
            int tm = (tl+tr)/2;
            build(arr,v*2+1,tl,tm);
            build(arr,v*2+2,tm+1,tr);
            t[v] = Math.max(t[v*2+1],t[v*2+2]);
        }
    }
    public int bestFitness(int v, int tl, int tr, int l, int r){
        if(r<tl || l>tr){
            return Integer.MIN_VALUE;
        }
        if(l == tr && r == tr){
            return t[v];
        }
        int tm = (tl+tr)/2;
        int left = bestFitness(v*2+1,tl,tm,l,Math.min(r,tm));
        int right = bestFitness(v*2+2,tm+1,tr,Math.max(l,tm+1),r);
        return Math.max(left,right);
    }
    public void improveFitness(int v, int tl, int tr, int index, int value){
        if(tl == tr){
            t[v] = value;
        }
        else{
            int tm = (tl+tr)/2;
            if(index <= tm){
                improveFitness(v*2+1, tl, tm, index, value);
            }
            else{
                improveFitness(v*2+2, tm+1, tr, index, value);
            }
            t[v] = Math.max(t[v*2+1], t[v*2+2]);
        }
    }
}

public class FitnessSegment{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        SegmentTree segTree = new SegmentTree(n);
        segTree.build(arr,0,0,n-1);
        for(int i = 0;i<q;i++){
            int option = sc.nextInt();
            if(option == 1){
                System.out.println(segTree.bestFitness(0,0,n-1,sc.nextInt(),sc.nextInt()));
            }
            else{
                segTree.improveFitness(0,0,n-1,sc.nextInt(),sc.nextInt());
            }
        }
        sc.close();
    }
}