// Given N satellite stations, numbered 1 to N.
// These satellites are connected to send signals from one to other.
// To send a signal from satellite 's' to satellite 'd', it takes 
// an amount of time 't'.

// You are given a list of travel times as directed edges times[i] = (s, d, t).

// Your task to find the time taken to recieve signal from a satellite station K, 
// to all N-1 satellite stations. If it is impossible, return -1 .

// Input Format:
// -------------
// Line-1 ->   Three integers, N number of satellite stations, 
//             K is the satellite to send signal and T is the number of edges.
// Next T lines -> Three space separated integers, 's' is the source, 
//             'd' is the destination, 
// 			't' is the time taken recieve signal from 's' to 'd'.

// Output Format:
// --------------
// Print an integer as your result.


// Sample Input-1:
// ---------------
// 4 2 3
// 2 1 1
// 2 3 1
// 3 4 1

// Sample Output-1:
// ----------------
// 2


// Sample Input-2:
// ---------------
// 5 2 4
// 2 1 1
// 2 3 2
// 3 4 3
// 5 1 4

// Sample Output-2:
// ----------------
// -1


// Sample Input-3:
// ---------------
// 5 2 4
// 2 1 1
// 2 3 2
// 3 4 3
// 1 5 6

// Sample Output-3:
// ----------------
// 7

import java.util.*;

public class ReceiveSignal{
    public static int getSignalTime(int n, int k, Map<Integer, List<int[]>> graph){
        int res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});

        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int index = top[0];
            int time = top[1];
            res = Math.max(res, time);
            if(graph.containsKey(index)){
                for(int[] neighbor : graph.get(index)){
                    pq.offer(new int[]{neighbor[0], time + neighbor[1]});
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int t = sc.nextInt();
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < t; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int time = sc.nextInt();
            graph.putIfAbsent(s, new ArrayList<>());
            graph.get(s).add(new int[]{d, time});
        }
        System.out.println(getSignalTime(n, k, graph));
        sc.close();
    }
}