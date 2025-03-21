// Imagine you are designing a treasure hunt that takes participants through 
// n unique landmarks, each identified by a distinct number from 1 to n. 
// The official treasure map provides an itinerary—called the route—which is a 
// complete ordering (a permutation) of these landmarks. Along the way, m clues 
// are given in the form of several smaller landmark sequences. Each clue is a pair
// of landmarks that appear in the route in the same order, though not necessarily 
// consecutively.

// Your challenge is to determine whether the provided route is both the shortest 
// possible itinerary that incorporates all the clues and the only such itinerary. 
// The shortest itinerary is defined as one that contains every clue as an ordered 
// subsequence while including no extra landmarks beyond what is necessary. Although
// there might be multiple itineraries that include all clues, the route must be 
// unique and minimal for your treasure hunt to be considered well-defined.

// Display valid if the official route is the only shortest itinerary that fits all 
// the clues, or invalid otherwise.


// Input format:
// -------------
// Line 1: n m, space seperated 2 integers
// Line 2: n space seperated integers, representing official route
// Line 3: m lines of clues, each clue has 2 space separated integers

// Output format:
// ---------------
// official route is valid or official route is invalid.


// Example 1:  
// input=
// 3 2
// 1 3 2
// 1 2
// 1 3
// output=
// [1, 3, 2] is invalid

// Explanation: 
// - There are two possible itineries: [1,2,3] and [1,3,2].
// - The clue [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
// - The clue [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
// - Since there are multiple valid shortest itineraries, the official route is not unique.

// Example 2: 
// input=
// 3 1
// 1 2 3
// 1 2
// output=
// [1, 2, 3] is invalid

// Explanation: 
// - The shortest possible itinerary is [1,2].
// - The clue [1,2] is a subsequence of it: [1,2].
// - Since official route is not the shortest itinerary, we return false.

// Example 3:
// input=
// 3 3
// 1 2 3
// 1 2
// 1 3
// 2 3
// Output=
// [1, 2, 3] is valid
  
// Explanation: 
// - The only possible shortest itinerary that contains all the clues is [1, 2, 3]. 
// - No alternative minimal route exists. 

import java.util.*;
import java.util.LinkedList;
public class ShortestItenary{
    public static void addEdge(int u, int v, HashMap<Integer,ArrayList<Integer>> map){
        map.putIfAbsent(u,new ArrayList<>());
        map.get(u).add(v);
    }
    public static boolean isValid(int[][] arr, int n, int m, ArrayList<Integer> path, HashMap<Integer,ArrayList<Integer>> map){
        HashMap<Integer,Integer> indegree = new HashMap<>();
        for(int[] i : arr){
            indegree.put(i[1],indegree.getOrDefault(i[1],0)+1);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1;i<=n;i++){
            if(!indegree.containsKey(i)){
                q.offer(i);
            }
        }
        ArrayList<Integer> check = new ArrayList<>();
        if(q.size() > 1){
            return false;
        }
        while(!q.isEmpty()){
            int top = q.poll();
            if(q.size() > 1){
                return false;
            }
            check.add(top);
            if(map.containsKey(top)){
                for(int neigh : map.get(top)){
                    indegree.put(neigh,indegree.get(neigh)-1);
                    if(indegree.get(neigh) == 0){
                        indegree.remove(neigh);
                        q.offer(neigh);
                    }
                }
            }
        }
        return path.equals(check);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[m][2];
        ArrayList<Integer> path = new ArrayList<>();
        for(int i = 0;i<n;i++){
            path.add(sc.nextInt());
        }
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0;i<m;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            addEdge(arr[i][0],arr[i][1],map);
        }
        if(isValid(arr,n,m,path,map)){
            System.out.println(path + " is valid");
        }
        else{
            System.out.println(path + " is invalid");
        }
        sc.close();
    }
}