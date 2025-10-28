// Amith decided go on a holiday trip. 
// Given a map of N holiday spots numbered 0,1,2,.. N-1, map shows the connecting 
// routes between the holiday sopts, the routes are bidirectional, and
// the connecting routes are indicates as routes[i] = [from , to , distance ]. 
// He can travel only a certian distance D.

// Your task is to find the holiday spot H with the smallest number of holiday-spots
// that are reachable from H and whose distance is at most D, 

// If there are multiple holiday spots, return H with the greatest number.


// Input Format:
// -------------
// Line-1 : Three integers, N number of holiday spots, R is the number of connecting 
//         routes and D is the distance can travel.
// Next R lines : Three space separated integers, from , to and distance.

// Output Format:
// --------------
// Print an integer, holiday spot.


// Sample Input-1:
// ---------------
// 4 4 4
// 0 1 3
// 1 2 1
// 1 3 4
// 2 3 1

// Sample Output-1:
// ----------------
// 3


// Explanation:
// ------------
// Distance can travel= 4,

// From			Reachable sopts
// -------------------------------------
// Holiday-spot 0 -> [Holiday-spot 1, Holiday-spot 2] 
// Holiday-spot 1 -> [Holiday-spot 0, Holiday-spot 2, Holiday-spot 3] 
// Holiday-spot 2 -> [Holiday-spot 0, Holiday-spot 1, Holiday-spot 3] 
// Holiday-spot 3 -> [Holiday-spot 1, Holiday-spot 2] 

// Holiday-spots 0 and 3 have 2 reachable Holiday-spots at a distance = 4, 
// but we have to return Holiday-spot 3 since it has the greatest number.


// Sample Input-2:
// ---------------
// 5 6 2
// 0 1 2
// 0 4 8
// 1 2 3
// 1 4 2
// 2 3 1
// 3 4 1

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// Distance can travel= 2,

// From			Reachable sopts
// -------------------------------------
// Holiday-spot 0 -> [Holiday-spot 1] 
// Holiday-spot 1 -> [Holiday-spot 0, Holiday-spot 4] 
// Holiday-spot 2 -> [Holiday-spot 3, Holiday-spot 4] 
// Holiday-spot 3 -> [Holiday-spot 2, Holiday-spot 4]
// Holiday-spot 4 -> [Holiday-spot 1, Holiday-spot 2, Holiday-spot 3]

// Holiday-spots 0 has 1 reachable Holiday-spot at a distance = 2, 

import java.util.*;

public class HolidaySpot{
    public static void main (String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            int d = sc.nextInt();
            List<List<int[]>> map = new ArrayList<>();
            for(int i = 0; i < n; i++){
                map.add(new ArrayList<>());
            }
            for(int i = 0; i < r; i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                int dist = sc.nextInt();
                map.get(from).add(new int[]{to,dist});
                map.get(to).add(new int[]{from,dist});
            }
            System.out.println(find(map,d,n));
        }
    }
    static int find(List<List<int[]>> map , int d, int n){
        int res = Integer.MAX_VALUE;
        int best = 0;
        for(int i = 0; i < n; i++){
            int val = dijkstra(map,i,d); 
            if(res>val){
                res = val;
                best = i;
            }
            else if(res==val){
                best = Math.max(best,i);
            }
        }
        
        return best;
    
    }
    static int dijkstra(List<List<int[]>> map , int p, int d){
        int[] dist = new int[map.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[p] = 0;  
    
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.add(new int[]{p, 0});
    
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int u = node[0];
            int currDist = node[1];
    
            if(currDist > dist[u]) continue; 
    
            for(int[] nbr : map.get(u)){
                int v = nbr[0];
                int w = nbr[1];
                int newD = currDist + w;
    
                if(newD < dist[v] && newD <= d){
                    dist[v] = newD;
                    pq.add(new int[]{v, newD});
                }
            }
        }
    
        int count = 0;
        for(int i = 0; i < dist.length; i++){
            if(i != p && dist[i] <= d){
                count++;
            }
        }
        return count;
    }
}