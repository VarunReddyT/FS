// Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
// spectacular dinner consisting of numDishes unique dishes, labeled from 
// 0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
// only be prepared after completing others. You’re given a list called dependecies, 
// where each element dependencies[i] = [Xi, Yi] means that you must finish 
// preparing dish Yi before starting dish Xi.

// For instance, the pair [2, 1] implies that to create dish 2, 
// dish 1 must be prepared first.

// Return the ordering of dishes that a chef should take to finish all dishes.
// 	- the result set should follow the given order of conditions.
// 	- If it is impossible to complete all dishes, return an empty set.


// Input Format:
// -------------
// Line-1: An integer numDishes, number of Dishes.
// Line-2: An integer m, number of dependencies.
// Next m lines: Two space separated integers, Xi and Yi.

// Output Format:
// --------------
// Return a list of integers, the ordering of dishes that a chef should finish.

// Example 1:
// ------------
// Input=
// 4
// 3
// 1 2
// 3 0
// 0 1
// Output=
// [2, 1, 0, 3]


// Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
// dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


// Example 2:
// ----------
// Input=
// 2
// 2
// 1 0
// 0 1
// Output=
// []

// Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
// on dish 1. This circular dependency makes it impossible to prepare all dishes.

// Constraints:

// - 1 <= numDishes <= 2000  
// - 0 <= m <= 5000  
// - dependencies[i].length == 2  
// - 0 <= Xi, Yi < numDishes  
// - All the dependency pairs are unique.


import java.util.*;
import java.util.LinkedList;

public class OrderDishes{
    public static void addEdge(int u, int v, HashMap<Integer,ArrayList<Integer>> map){
        map.putIfAbsent(v,new ArrayList<>());
        map.get(v).add(u);
    }
    public static ArrayList<Integer> getDishes(int[][] arr, int n, int m, HashMap<Integer,ArrayList<Integer>> map){
        HashMap<Integer,Integer> indegree = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int[] i : arr){
            indegree.put(i[0],indegree.getOrDefault(i[0],0)+1);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<n;i++){
            if(!indegree.containsKey(i)){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int top = q.poll();
            res.add(top);
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
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        int[][] arr = new int[m][2];
        for(int i = 0;i<m;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            addEdge(arr[i][0],arr[i][1],map);
        }
        System.out.println(getDishes(arr,n,m,map));
        sc.close();
    }
}